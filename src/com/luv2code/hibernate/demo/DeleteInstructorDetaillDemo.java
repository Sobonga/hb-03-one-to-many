package com.luv2code.hibernate.demo;

import com.luv2code.hibernate.demo.entity.Instructor;
import com.luv2code.hibernate.demo.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteInstructorDetaillDemo {

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

            //start a transaction
            session.beginTransaction ();

            //get instructor detail object
            int theId = 3;

            InstructorDetail tempInstructorDetail = session.get ( InstructorDetail.class,theId );

            //print instructor detail
            System.out.println ("tempInstructorDetail: "+tempInstructorDetail);


            //print the associated instructor
            System.out.println ("the associated instructor: "+ tempInstructorDetail.getInstructor ());

            //now let's delete the instructor detail
            System.out.println ("Deleting tempInstructorDetail: "  +tempInstructorDetail);


            //remove the associated object reference
            //break bi-directional  link
            tempInstructorDetail.getInstructor ().setInstructorDetail ( null );

            //Remove tempInstructor
            session.delete ( tempInstructorDetail );


            //commit transaction
            session.getTransaction ().commit ();
            System.out.println ("Done!!!");

        }
        catch (Exception e){
            e.printStackTrace ();
        }
        finally {
            //handle connection leak issue
            session.close ();
            factory.close ();
        }
    }
}
