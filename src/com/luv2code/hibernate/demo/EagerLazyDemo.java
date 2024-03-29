package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class EagerLazyDemo {

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

            System.out.println ("luv2code::Instructor: " +tempInstructor);

            System.out.println ("luv2code::Courses: " +tempInstructor.getCourses ());


            //commit transaction
            session.getTransaction ().commit ();
            session.close ();
            System.out.println ("Luv2code::: Session now closed\n");
            //option 1: call getter method while session is open




            System.out.println ("luv2code::Courses: " +tempInstructor.getCourses ());
            System.out.println ("Done!!!");

        }
        finally {
            //add clean up code
            session.close ();
            factory.close ();
        }
    }
}
