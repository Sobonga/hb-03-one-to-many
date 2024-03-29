package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCoursesDemo {

    public static void main(String[]args){


        //Create SessionFactory
        SessionFactory factory = new Configuration ()
                .configure ("hibernate.cfg.xml")
                .addAnnotatedClass (Instructor.class )
                .addAnnotatedClass (InstructorDetail.class )
                .addAnnotatedClass (Course.class )
                .buildSessionFactory ();

        //Create Session
        Session session = factory.getCurrentSession ();


        try{


            //start a transaction
            session.beginTransaction ();

          //get the inbstructor from db
            int theId =1;

            Instructor tempInstructor = session.get ( Instructor.class,theId );

            //create some courses
            Course tempCourse1 = new Course ("Air Guitar- Ultimate guid" );
            Course tempCourse2 = new Course ("The Pinball masterclass" );

            //add courses to instructor
            tempInstructor.add ( tempCourse1 );
            tempInstructor.add ( tempCourse2 );

            //save the courses
            session.save ( tempCourse1 );
            session.save ( tempCourse2 );

            //commit transaction
            session.getTransaction ().commit ();
            System.out.println ("Done!!!");

        }
        finally {
            //add clean up code
            session.close ();
            factory.close ();
        }
    }
}
