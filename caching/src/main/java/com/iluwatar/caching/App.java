/**
 * The MIT License
 * Copyright (c) 2014-2016 Ilkka Seppälä
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.iluwatar.caching;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * The Caching pattern describes how to avoid expensive re-acquisition of resources by not releasing
 * the resources immediately after their use. The resources retain their identity, are kept in some
 * fast-access storage, and are re-used to avoid having to acquire them again. There are four main
 * caching strategies/techniques in this pattern; each with their own pros and cons. They are;
 * <code>write-through</code> which writes data to the cache and DB in a single transaction,
 * <code>write-around</code> which writes data immediately into the DB instead of the cache,
 * <code>write-behind</code> which writes data into the cache initially whilst the data is only
 * written into the DB when the cache is full, and <code>cache-aside</code> which pushes the
 * responsibility of keeping the data synchronized in both data sources to the application itself.
 * The <code>read-through</code> strategy is also included in the mentioned four strategies --
 * returns data from the cache to the caller <b>if</b> it exists <b>else</b> queries from DB and
 * stores it into the cache for future use. These strategies determine when the data in the cache
 * should be written back to the backing store (i.e. Database) and help keep both data sources
 * synchronized/up-to-date. This pattern can improve performance and also helps to maintain
 * consistency between data held in the cache and the data in the underlying data store.
 *
 * 缓存模式主要介绍四种：
 * 1.write-through：在单个事务中写入数据到缓存和DB中
 * 2.write-around：将数据立即写入数据库而不是缓存
 * 3.write-behind：首先把数据写入到缓存，当缓存满的时候将数据写入到DB。
 * 4.cache-aside：负责将两个数据源中的数据同步到应用程序
 *
 * read-through:包含了上面的四种策略--
 *  如果数据在缓存中存在，返回数据给调用者，否则从db中查同时存入cache中供未来使用。
 * <p>
 * In this example, the user account ({@link UserAccount}) entity is used as the underlying
 * application data. The cache itself is implemented as an internal (Java) data structure. It adopts
 * a Least-Recently-Used (LRU) strategy for evicting data from itself when its full. The four
 * strategies are individually tested. The testing of the cache is restricted towards saving and
 * querying of user accounts from the underlying data store ( {@link DbManager}). The main class (
 * {@link App} is not aware of the underlying mechanics of the application (i.e. save and query) and
 * whether the data is coming from the cache or the DB (i.e. separation of concern). The AppManager
 * ({@link AppManager}) handles the transaction of data to-and-from the underlying data store
 * (depending on the preferred caching policy/strategy).
 *
 * <i>App --> AppManager --> CacheStore/LRUCache/CachingPolicy --> DBManager</i>
 * </p>
 *
 * @see CacheStore
 * @See LRUCache
 * @see CachingPolicy
 *
 * PS：这个设计模式实现有问题
 */
public class App {

  private static final Logger LOGGER = LoggerFactory.getLogger(App.class);


  /**
   * Program entry point
   * 推荐使用Read-through 和write-around
   * @param args command line args
   */
  public static void main(String[] args) {
    AppManager.initDb(false); // VirtualDB (instead of MongoDB) was used in running the JUnit tests
                              // and the App class to avoid Maven compilation errors. Set flag to
                              // true to run the tests with MongoDB (provided that MongoDB is
                              // installed and socket connection is open).
    AppManager.initCacheCapacity(3);
    App app = new App();
//    app.useReadAndWriteThroughStrategy();
//    app.useReadThroughAndWriteAroundStrategy();
//    app.useReadThroughAndWriteBehindStrategy();
    app.useCacheAsideStategy();
  }

  /**
   * Read-through and write-through 读穿透和写穿透
   */
  public void useReadAndWriteThroughStrategy() {
    LOGGER.info("# CachingPolicy.THROUGH");
    AppManager.initCachingPolicy(CachingPolicy.THROUGH);

    UserAccount userAccount1 = new UserAccount("001", "John", "He is a boy.");
    /* 写逻辑：缓存中包含就更新DB，否则直接插入DB。 最后重新设置缓存  */
    AppManager.save(userAccount1);
    LOGGER.info(AppManager.printCacheContent());
    AppManager.find("001");
    AppManager.find("001");
  }

  /**
   * Read-through and write-around 读穿透和写环绕
   */
  public void useReadThroughAndWriteAroundStrategy() {
    LOGGER.info("# CachingPolicy.AROUND");
    AppManager.initCachingPolicy(CachingPolicy.AROUND);

    UserAccount userAccount2 = new UserAccount("002", "Jane", "She is a girl.");
    /* 写逻辑：缓存中包含就更新DB并移除过期缓存，否则直接插入DB  */
    //更新时数据必须在缓存中，如果不在会有问题。
    AppManager.save(userAccount2);
    LOGGER.info(1 + AppManager.printCacheContent());
    /* 读逻辑： cache中取，直接返回。 如果miss，从db中读并设置到cache中返回。 */
    AppManager.find("002");
    LOGGER.info(2 + AppManager.printCacheContent());
    userAccount2 = AppManager.find("002");
    userAccount2.setUserName("Jane G.");
    AppManager.save(userAccount2);
    LOGGER.info(3+ AppManager.printCacheContent());
    AppManager.find("002");
    LOGGER.info(4+ AppManager.printCacheContent());
    AppManager.find("002");
  }

  /**
   * Read-through and write-behind
   */
  public void useReadThroughAndWriteBehindStrategy() {
    LOGGER.info("# CachingPolicy.BEHIND");
    AppManager.initCachingPolicy(CachingPolicy.BEHIND);
/* 读逻辑： cache中取，直接返回。 如果miss，从db中读并设置到cache中返回。 */
/* 写逻辑：直接写入cache中。 cache是LRU，如果cache满了，取队尾数据存入db   */
//缺点： cache一挂，会造成数据丢失。需要缓存中间件可靠。
    UserAccount userAccount3 = new UserAccount("003", "Adam", "He likes food.");
    UserAccount userAccount4 = new UserAccount("004", "Rita", "She hates cats.");
    UserAccount userAccount5 = new UserAccount("005", "Isaac", "He is allergic to mustard.");

    AppManager.save(userAccount3);
    AppManager.save(userAccount4);
    AppManager.save(userAccount5);
    LOGGER.info(AppManager.printCacheContent());
    AppManager.find("003");
    LOGGER.info(AppManager.printCacheContent());
    UserAccount userAccount6 = new UserAccount("006", "Yasha", "She is an only child.");
    AppManager.save(userAccount6);
    LOGGER.info(AppManager.printCacheContent());
    AppManager.find("004");
    LOGGER.info(AppManager.printCacheContent());
  }

  /**
   * Cache-Aside
   */
  public void useCacheAsideStategy() {
    LOGGER.info("# CachingPolicy.ASIDE");
    AppManager.initCachingPolicy(CachingPolicy.ASIDE);
    LOGGER.info(AppManager.printCacheContent());
    /* 写逻辑：更新DB，cache过期掉数据*/
    UserAccount userAccount3 = new UserAccount("003", "Adam", "He likes food.");
    UserAccount userAccount4 = new UserAccount("004", "Rita", "She hates cats.");
    UserAccount userAccount5 = new UserAccount("005", "Isaac", "He is allergic to mustard.");
    AppManager.save(userAccount3);
    AppManager.save(userAccount4);
    AppManager.save(userAccount5);

    LOGGER.info(AppManager.printCacheContent());
    /* 读逻辑：先从cache中读，若有则返回。 再从db中读，如果有设置缓存返回。没有直接返回。 */
    // 存在问题：一直读没有的数据，会一直查DB，造成数据库攻击
    AppManager.find("003");
    LOGGER.info(AppManager.printCacheContent());
    AppManager.find("004");
    LOGGER.info(AppManager.printCacheContent());
  }
}
