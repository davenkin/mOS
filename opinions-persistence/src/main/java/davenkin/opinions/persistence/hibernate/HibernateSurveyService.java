package davenkin.opinions.persistence.hibernate;

import davenkin.opinions.domain.Category;
import davenkin.opinions.domain.Option;
import davenkin.opinions.domain.Survey;
import davenkin.opinions.persistence.service.SurveyService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 3/31/13
 * Time: 5:24 PM
 * To change this template use File | Settings | File Templates.
 */
public class HibernateSurveyService implements SurveyService {
    private SessionFactory sessionFactory;

    @Override
    public List<Survey> getAllSurveys() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Survey> getSurveysByTag(String tag) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Survey> getSurveysByCategory(Category category) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public List<Survey> getSurveysCreatedByUser(long userId) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    @Transactional
    public Survey getSurveyById(long surveyId) {
        Session session = sessionFactory.getCurrentSession();
     return (Survey) session.load(Survey.class, surveyId);
    }

    @Override
    @Transactional
    public long addSurvey(final Survey survey) {
        final long[] surveyId = new long[1];
        new CurrentSessionTemplate() {
            @Override
            public void doInCurrentSession(Session session) {

        surveyId[0] = (Long) session.save(survey);
            }
        }.doInSessionFactory(sessionFactory);
        return surveyId[0];
    }

    @Override
    @Transactional
    public void takeSurvey(final long optionId) {
        new CurrentSessionTemplate() {
            @Override
           public void doInCurrentSession(Session session) {
                Option option = (Option)session.load(Option.class, optionId);
                option.vote();

            }
        }.doInSessionFactory(sessionFactory);
            }


    @Override
    @Transactional
    public void removeSurvey(long surveyId) {
        Session session = sessionFactory.getCurrentSession();
        Object survey = session.load(Survey.class, surveyId);
        session.delete(survey);
    }

    @Required
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
