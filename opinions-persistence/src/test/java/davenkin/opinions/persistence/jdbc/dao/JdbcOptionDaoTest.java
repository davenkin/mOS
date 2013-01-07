package davenkin.opinions.persistence.jdbc.dao;

import davenkin.opinions.domain.Option;
import davenkin.opinions.domain.DataAccessException;
import davenkin.opinions.persistence.DataSourceUtil;
import davenkin.opinions.persistence.dao.OptionDao;
import davenkin.opinions.persistence.jdbc.dao.JdbcOptionDao;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class JdbcOptionDaoTest
{
    private OptionDao optionDao;

    @Before
    public void setUp() throws Exception
    {
        optionDao = new JdbcOptionDao(DataSourceUtil.createDataSource());
    }


    @Test
    public void testFindOptionsForSurvey() throws Exception
    {
        List<Option> options = optionDao.findOptionsForSurvey(1L);
        assertTrue(options.size() == 3);
        assertThat(options.get(0).getOption(), is("More than 10 years"));
    }

    @Test
    public void voteForOption() throws DataAccessException
    {
        long previousCount = optionDao.findOption(10l).getCount();
        optionDao.increaseOptionCount(10l);
        long votedCount = optionDao.findOption(10l).getCount();
        assertTrue((votedCount - previousCount) == 1);
    }
}