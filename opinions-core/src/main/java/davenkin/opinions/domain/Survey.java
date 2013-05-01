package davenkin.opinions.domain;

import com.google.common.base.Predicate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.FluentIterable.from;

/**
 * Created with IntelliJ IDEA.
 * User: davenkin
 * Date: 12/23/12
 * Time: 7:54 PM
 * To change this template use File | Settings | File Templates.
 */
public class Survey {
    private long id;
    private String content;
    private long userId;
    private Timestamp createdTime;
    private boolean canMultipleChecked;
    private Category surveyCategory;
    private Set<String> surveyTags = new HashSet<String>();
    private List<Option> options = new ArrayList<Option>();

    public Survey(long userId, String content, boolean canMultipleChecked, Category surveyCategory, List<String> optionNames, Set<String> surveyTags) {
        this.userId = userId;
        this.content = content;
        this.canMultipleChecked = canMultipleChecked;
        this.surveyCategory = surveyCategory;
        this.surveyTags = surveyTags;
        this.options = createOptions(optionNames);
        this.createdTime = new Timestamp(System.currentTimeMillis());
    }

    public Option getOption(final long optionId) {
        return from(options).firstMatch(new Predicate<Option>() {
            @Override
            public boolean apply(Option input) {
                return input.getId() == optionId;
            }
        }).get();
    }


    public Set<String> getSurveyTags() {
        return surveyTags;
    }


    public void addTag(String tag) {
        surveyTags.add(tag);
    }

    public void removeTag(String tag) {
        surveyTags.remove(tag);
    }

    private List<Option> createOptions(List<String> optionNames) {
        ArrayList<Option> options = new ArrayList<Option>();
        for (String optionName : optionNames) {
            options.add(new Option(this, optionName));
        }
        return options;
    }

    public String getContent() {
        return content;
    }

    public Category getSurveyCategory() {
        return surveyCategory;
    }

    public List<Option> getOptions() {
        return options;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Survey survey = (Survey) o;

        if (!content.equals(survey.content)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return content.hashCode();
    }
}
