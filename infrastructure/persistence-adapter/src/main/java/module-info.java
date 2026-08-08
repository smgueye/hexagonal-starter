import com.github.smgueye.app.persistenceadapter.PostgresProvider;
import core.lib.AdapterProvider;

module persistence.adapter {
  requires org.hibernate.orm.core;

  requires jakarta.persistence;

  requires java.sql;

  requires spring.context;
  requires spring.data.jpa;
  requires spring.data.commons;

  requires static lombok;

  requires core;
  requires domain;
  requires spring.boot.persistence;
  requires spring.boot;
  requires spring.boot.autoconfigure;
  requires spring.tx;
  requires org.jspecify;

  opens com.github.smgueye.app.persistenceadapter;

  exports com.github.smgueye.app.persistenceadapter;

  provides AdapterProvider with PostgresProvider;
}