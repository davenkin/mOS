package davenkin.opinions.persistence.jdbc.service;

import davenkin.opinions.domain.User;
import davenkin.opinions.persistence.DataSourceUtil;
import davenkin.opinions.persistence.jdbc.JdbcTransactionManager;
import davenkin.opinions.persistence.service.UserService;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class JdbcUserServiceTest
{

    private UserService proxyService;

    @Before
    public void setUp() throws Exception
    {
        BasicDataSource dataSource = DataSourceUtil.createDataSource();
        TransactionEnabledProxy transactionEnabledProxy = new TransactionEnabledProxy(new JdbcTransactionManager(dataSource));
        JdbcUserService jdbcUserService = new JdbcUserService(dataSource);

        proxyService = (UserService) transactionEnabledProxy.proxyFor(jdbcUserService);
    }


    @Test
    public void getUserById()
    {
        User userById = proxyService.getUserById(1);
        assertThat(userById.getName(), is("davenkin"));
    }

    @Test
    public void updateUserName()
    {
        proxyService.updateUserName(1, "newName");
        User userById = proxyService.getUserById(1);
        assertThat(userById.getName(), is("newName"));
        proxyService.updateUserName(1, "davenkin");
    }

    @Test
    public void shouldNotUpdateWhenException()
    {
        proxyService.updateUserName(1, null);
        assertThat(proxyService.getUserById(1).getName(), is("davenkin"));

    }


}
