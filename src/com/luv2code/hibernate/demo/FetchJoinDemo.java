package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import org.hibernate.query.Query;

public class FetchJoinDemo {

    public static void main(String[] args) {


        //Create SessionFactory
        SessionFactory factory = new Configuration ()
                .configure ( "hibernate.cfg.xml" )
                .addAnnotatedClass ( Instructor.class )
                .addAnnotatedClass ( InstructorDetail.class )
                .addAnnotatedClass ( Course.class )
                .buildSessionFactory ();

        //Create Session
        Session session = factory.getCurrentSession ();


        try {


            //start a transaction
            session.beginTransaction ();

            //option 2: Hibernate query with HQl


            //get the inbstructor from db
            int theId = 1;

            Query<Instructor> query = session.createQuery ( "SELECT i FROM Instructor i JOIN FETCH i.course WHERE i.id=:theInstructorId",Instructor.class );

            //set parameter on query
            query.setParameter ( "theInstructorId",theId );

            //execute query and get instructor
            Instructor tempInstructor= query.getSingleResult ();
            System.out.println ( "luv2code::Instructor: " + tempInstructor );

            //commit transaction
            session.getTransaction ().commit ();
            session.close ();
            System.out.println ( "Luv2code::: Session now closed\n" );
            //option 1: call getter method while session is open

            System.out.println ( "luv2code::Courses: " + tempInstructor.getCourses () );
            System.out.println ( "Done!!!" );

        } finally {
            //add clean up code
            session.close ();
            factory.close ();
        }
    }
}
