package biao.community.service;

import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantLock;

@Service
public class SynchronizedByKey {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    Map<String, ReentrantLock> mutexCache = new ConcurrentHashMap<>();
    public String exec(String key,Runnable statement ) {
        ReentrantLock mutex4Key = null;
        ReentrantLock mutexInCache;
        do {
            if (mutex4Key != null) {
                mutex4Key.unlock();
            }
            mutex4Key = mutexCache.computeIfAbsent(key, k -> new ReentrantLock());

            mutex4Key.lock();
            mutexInCache = mutexCache.get(key);
        } while (mutexInCache == null || mutex4Key != mutexInCache);
        try {
            statement.run();
        } finally {
            if(mutex4Key.getQueueLength() == 0){
                mutexCache.remove(key);
            }
            mutex4Key.unlock();
        }
        return "1";
    }
}
