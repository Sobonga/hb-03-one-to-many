package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Course;
import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateInstructorDemo {

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
            //create the object

            Instructor tempInstructor = new Instructor ("Susan","Public","susan.public@luv2code.com"  );

            InstructorDetail tempInstructorDetail = new InstructorDetail ( "http://www.youtube.com","Video Games!" );

            //start a transaction
            session.beginTransaction ();

            //associate the objects
            tempInstructor.setInstructorDetail ( tempInstructorDetail );

            //save instructor
            //NB: This will also save the details object

            System.out.println ("Saving Instruction: "+ tempInstructor);
            session.save ( tempInstructor );





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
