package com.github.smgueye.app.persistenceadapter;

import core.lib.Transaction;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.function.Supplier;


public class TransactionSpring implements Transaction {

  private final TransactionTemplate transactionTemplate;

  public TransactionSpring(PlatformTransactionManager transactionManager) {
    this.transactionTemplate = new TransactionTemplate(transactionManager);
  }

  @Override
  public <T> T executer(Supplier<T> handler) throws Exception {
    return transactionTemplate.execute(status -> handler.get());
  }
}
