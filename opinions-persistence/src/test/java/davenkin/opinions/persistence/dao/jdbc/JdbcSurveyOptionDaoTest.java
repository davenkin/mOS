package davenkin.opinions.persistence.dao.jdbc;

import davenkin.opinions.domain.Option;
import davenkin.opinions.persistence.DataSourceUtil;
import davenkin.opinions.persistence.dao.SurveyOptionDao;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class JdbcSurveyOptionDaoTest
{
    private SurveyOptionDao optionDao;

    @Before
    public void setUp() throws Exception
    {
        optionDao = new JdbcSurveyOptionDao(DataSourceUtil.createDataSource());
    }


    @Test
    public void testFindOptionsForSurvey() throws Exception
    {
        List<Option> options = optionDao.findOptionsForSurvey(1L);
        assertTrue(options.size() == 3);
        assertThat(options.get(0).getOption(), is("More than 10 years"));
    }
}
