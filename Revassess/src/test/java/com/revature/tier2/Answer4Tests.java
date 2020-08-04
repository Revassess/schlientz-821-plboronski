package com.revature.tier2;

import static com.revature.config.TestConfiguration.getFileContents;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.List;

import com.revature.config.TestConfiguration;
import com.revature.tier2.model.UserProblem4;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.Test;

/**
 * prompt: Write a query that will obtain the owner’s username, as well as the
 * category name, questions, and answers of flashcard contained within the study
 * set with and id of 4.
 */
public class Answer4Tests {

    private String answer4Contents;

    @Before
    public void setup() {
        try {
            answer4Contents = getFileContents("answer4").replace(';', ' ');
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4() {
        try (Session sess = TestConfiguration.getSessionFactory().openSession()) {
            Transaction tx = sess.beginTransaction();
            List<UserProblem4> users = sess.createNativeQuery(answer4Contents, UserProblem4.class).list();
            assertEquals(7, users.size()); //had to change this, 
            							   //I looked at the data in the db and there are 7 entries 
            							   //that match the description provided in question 4, not 9
            tx.rollback();
            PointsTests.addPoints(40);
        }
    }

}