package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import com.luv2code.hibernate.demo.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateDemo {

    public static void main(String[]args){


        //Create SessionFactory
        SessionFactory factory = new Configuration ()
                .configure ("hibernate.cfg.xml")
                .addAnnotatedClass (Instructor.class )
                .addAnnotatedClass (InstructorDetail.class )
                .buildSessionFactory ();

        //Create Session
        Session session = factory.getCurrentSession ();


        try{
            //create the object
/*            Instructor tempInstructor = new Instructor ("Chad","Derby","derby@luv2code.com"  );

            InstructorDetail tempInstructorDetail = new InstructorDetail ( "http://www.luv2code.com/youtube","Luv 2 Code!" );*/

            Instructor tempInstructor = new Instructor ("Madhu","Patel","madhu@luv2code.com"  );

            InstructorDetail tempInstructorDetail = new InstructorDetail ( "http://www.youtube.com","Guitar!" );

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
            factory.close ();
        }
    }
}
