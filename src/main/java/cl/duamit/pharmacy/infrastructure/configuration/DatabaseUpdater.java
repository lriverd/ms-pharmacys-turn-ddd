package cl.duamit.pharmacy.infrastructure.configuration;

import liquibase.Contexts;
import liquibase.LabelExpression;
import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.resource.ClassLoaderResourceAccessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

@Component
public class DatabaseUpdater {

	public final void update() throws Exception {

		Database database = DatabaseFactory.getInstance().getDatabase("myDb");
		Liquibase liquibase = new Liquibase("db/changelog/db.changelog-master.xml", new ClassLoaderResourceAccessor(), database);
		liquibase.update(new Contexts(), new LabelExpression());

	}
}
