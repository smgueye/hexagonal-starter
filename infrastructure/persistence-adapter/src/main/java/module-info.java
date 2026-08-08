import com.github.app.domain.PourGererLesProduits;
import com.github.smgueye.app.persistenceadapter.GestionnaireDeProduit;

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

  opens com.github.smgueye.app.persistenceadapter;

  exports com.github.smgueye.app.persistenceadapter;

  provides PourGererLesProduits with GestionnaireDeProduit;
}