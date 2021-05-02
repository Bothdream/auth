package com.zte.auth.config.multipledatasources;

import com.atomikos.icatch.jta.UserTransactionImp;
import com.atomikos.icatch.jta.UserTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.jta.JtaTransactionManager;

import javax.transaction.SystemException;
import javax.transaction.TransactionManager;
import javax.transaction.UserTransaction;

/**
 * 事务管理器
 */
@Configuration
public class XATransactionManagerConfig {
    /**
     * 用户事务
     * @return
     * @throws SystemException
     */
    @Bean(name = "userTransaction")
    public UserTransaction userTransaction() throws SystemException {
        UserTransactionImp userTransactionImp = new UserTransactionImp();
        userTransactionImp.setTransactionTimeout(10000);
        return userTransactionImp;
    }

    /**
     * 分布式事务
     * @return
     */
    @Bean(name = "atomikosTransactionManager",initMethod = "init",destroyMethod = "close")
    public TransactionManager atomikosTransactionManager() {
        UserTransactionManager userTransactionManager = new UserTransactionManager();
        userTransactionManager.setForceShutdown(false);
        return userTransactionManager;
    }

    /**
     * 事务管理器
     * @return
     * @throws SystemException
     */
    @Bean(name = "transactionManager")
    @DependsOn({"userTransaction","atomikosTransactionManager"})
    public PlatformTransactionManager transactionManager() throws SystemException {
        return new JtaTransactionManager(userTransaction(),atomikosTransactionManager());
    }
}
