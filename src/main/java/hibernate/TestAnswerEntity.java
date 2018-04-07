package hibernate;

import javax.persistence.*;

@Entity
@Table(name = "test_answer", schema = "online_course", catalog = "")
public class TestAnswerEntity {
    private int idTestAnswer;
    private int testQuestion;
    private String text;
    private byte isCorrect;
    private TestQuestionEntity testQuestionByTestQuestion;

    @Id
    @Column(name = "id_test_answer")
    public int getIdTestAnswer() {
        return idTestAnswer;
    }

    public void setIdTestAnswer(int idTestAnswer) {
        this.idTestAnswer = idTestAnswer;
    }

    @Basic
    @Column(name = "test_question")
    public int getTestQuestion() {
        return testQuestion;
    }

    public void setTestQuestion(int testQuestion) {
        this.testQuestion = testQuestion;
    }

    @Basic
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Basic
    @Column(name = "is_correct")
    public byte getIsCorrect() {
        return isCorrect;
    }

    public void setIsCorrect(byte isCorrect) {
        this.isCorrect = isCorrect;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestAnswerEntity that = (TestAnswerEntity) o;

        if (idTestAnswer != that.idTestAnswer) return false;
        if (testQuestion != that.testQuestion) return false;
        if (isCorrect != that.isCorrect) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idTestAnswer;
        result = 31 * result + testQuestion;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        result = 31 * result + (int) isCorrect;
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "test_question", referencedColumnName = "id_test_question", nullable = false, insertable = false, updatable = false)
    public TestQuestionEntity getTestQuestionByTestQuestion() {
        return testQuestionByTestQuestion;
    }

    public void setTestQuestionByTestQuestion(TestQuestionEntity testQuestionByTestQuestion) {
        this.testQuestionByTestQuestion = testQuestionByTestQuestion;
    }
}
