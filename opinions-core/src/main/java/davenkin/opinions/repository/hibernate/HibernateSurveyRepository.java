package davenkin.opinions.repository.hibernate;

import davenkin.opinions.domain.Category;
import davenkin.opinions.domain.Survey;
import davenkin.opinions.repository.SurveyRepository;
import davenkin.opinions.repository.hibernate.HibernateRepository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: twer
 * Date: 4/30/13
 * Time: 2:48 PM
 * To change this template use File | Settings | File Templates.
 */
public class HibernateSurveyRepository extends HibernateRepository implements SurveyRepository {
    @Override
    public long addSurvey(Survey survey) {
        return (Long) getCurrentSession().save(survey);
    }

    @Override
    public void updateSurvey(Survey survey) {
        getCurrentSession().update(survey);
    }

    @Override
    public Survey getSurvey(long id) {
        return (Survey) getCurrentSession().load(Survey.class, id);
    }

    @Override
    public void delete(Survey survey) {
        getCurrentSession().delete(survey);
    }

    @Override
    public List<Survey> findAllSurveys() {
        return getCurrentSession().createQuery("from Survey").list();
    }

    @Override
    public List<Survey> findSurveysByTag(String tag) {
        return getCurrentSession().createQuery("from Survey s where :tag in elements(s.surveyTags)").setParameter("tag", tag).list();
    }

    @Override
    public List<Survey> findSurveysByCategory(Category category) {
        return getCurrentSession().createQuery("from Survey s where s.surveyCategory = :category").setParameter("category", category).list();
    }

    @Override
    public List<Survey> findSurveysCreatedByUser(long userId) {
        return getCurrentSession().createQuery("from Survey s where s.userId = :userId").setParameter("userId", userId).list();
    }
}
