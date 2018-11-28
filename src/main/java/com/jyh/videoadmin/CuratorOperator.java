package com.jyh.videoadmin;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.*;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.apache.zookeeper.data.Stat;

/**
 * @author JYH
 * 2018/11/18 15:01
 */
public class CuratorOperator {

    // zk客户端
    private CuratorFramework client = null;

    /**
     * 实例化zk客户端
     */
    public CuratorOperator(){
        /**
         * 同步创建zk示例，原生api是异步的
         * curator链接zookeeper策略：ExponentialBackoffRetry
         * baseSleepTimeMs  初始sleep的时间
         * maxRetries  最大重试次数
         * maxSleepMs  最大重试时间
         */
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 5);

        /**
         * curator链接zookeeper的策略:RetryNTimes
         * n：重试的次数
         * sleepMsBetweenRetries：每次重试间隔的时间
         */
        RetryPolicy retryPolicy1 = new RetryNTimes(3, 5000);

        /**
         * curator链接zookeeper的策略:RetryOneTime
         * sleepMsBetweenRetry:每次重试间隔的时间
         */
        RetryPolicy retryPolicy2 = new RetryOneTime(5000);


        /**
         * 永远重试，不推荐使用
         */
		RetryPolicy retryPolicy4 = new RetryForever(5000);

        /**
         * curator链接zookeeper的策略:RetryUntilElapsed
         * maxElapsedTimeMs:最大重试时间
         * sleepMsBetweenRetries:每次重试间隔
         * 重试时间超过maxElapsedTimeMs后，就不再重试
         */
		RetryPolicy retryPolicy5 = new RetryUntilElapsed(2000, 3000);

        client = CuratorFrameworkFactory.builder()
                .connectString("188.131.133.149:2181")
                .connectionTimeoutMs(1000)
                .sessionTimeoutMs(1000)
                .retryPolicy(retryPolicy)
                .namespace("workspace")
                .build();

        client.start();
    }



    public void crud() throws Exception {
        // 实例化
        CuratorOperator cto = new CuratorOperator();

        // 创建节点
        String nodePath = "/super/imooc";
		byte[] data = "superme".getBytes();
		cto.client.create().creatingParentsIfNeeded()
			.withMode(CreateMode.PERSISTENT)
			.withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
			.forPath(nodePath, data);

        // 更新节点数据
		byte[] newData = "batman".getBytes();
		cto.client
				.setData().
				withVersion(0).
				forPath(nodePath, newData);

        // 删除节点
		cto.client
				.delete()
				  .guaranteed()					// 如果删除失败，那么在后端还是继续会删除，直到成功
				  .deletingChildrenIfNeeded()	// 如果有子节点，就删除
				  .withVersion(0)
				  .forPath(nodePath);



        // 读取节点数据
		Stat stat = new Stat();
		byte[] data1 = cto.client
				.getData()
				.storingStatIn(stat)
				.forPath(nodePath);
		System.out.println("节点" + nodePath + "的数据为: " + new String(data1));
		System.out.println("该节点的版本号为: " + stat.getVersion());
    }
}