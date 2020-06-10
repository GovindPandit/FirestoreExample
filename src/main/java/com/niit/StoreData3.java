package com.niit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;

@WebServlet("/StoreData3")
public class StoreData3 extends HttpServlet 
{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		try
		{
			FirestoreOptions firestoreOptions =
			    FirestoreOptions.getDefaultInstance().toBuilder()
			        .setProjectId("firststore-niit")
			        .build();
			Firestore db = firestoreOptions.getService();
			
			User user=new User();
			user.setUsername("Govind123");
			user.setEmail("govind@gmail.com");
			user.setMobile("7977518582");
			
			//Generate id
			String id= db.collection("users").document().getId();
			db.collection("users").document(id).set(user);
		}
		catch(Exception e)
		{
			PrintWriter out=resp.getWriter();
			out.println(e+"");
		}
	}
}
