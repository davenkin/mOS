package davenkin.opinions.persistence.hibernate;

import davenkin.opinions.domain.Survey;
import davenkin.opinions.persistence.service.TagService;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 4/1/13
 * Time: 1:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class HibernateTagService implements TagService {
  private   SessionFactory sessionFactory;


    @Override
    @Transactional
    public void addTagToSurvey(long surveyId, String tag) {
        Session session = sessionFactory.getCurrentSession();
      Survey survey= (Survey) session.load(Survey.class,surveyId);
        survey.addTag(tag);
    }

    @Override
    @Transactional
    public void removeTagFromSurvey(long surveyId, String tag) {
        Session session = sessionFactory.getCurrentSession();
        Survey survey= (Survey) session.load(Survey.class,surveyId);
        survey.removeTag(tag);

    }

    @Override
    @Transactional
    public Set<String> getTagsForSurvey(long surveyId) {
        Session session = sessionFactory.getCurrentSession();
        Survey survey = (Survey) session.get(Survey.class, surveyId);
        return survey.getSurveyTags();
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
