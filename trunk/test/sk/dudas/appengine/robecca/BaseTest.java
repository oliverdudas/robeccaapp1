package sk.dudas.appengine.robecca;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.transaction.TransactionConfiguration;

/**
 * Created by IntelliJ IDEA.
 * User: oli
 * Date: 28.4.2011
 * Time: 23:18
 * To change this template use File | Settings | File Templates.
 */
//@TransactionConfiguration(transactionManager="transactionManager", defaultRollback = true)
//@Transactional
@ContextConfiguration("classpath*:applicationContext-test.xml")
public abstract class BaseTest extends AbstractJUnit4SpringContextTests {
}