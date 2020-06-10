package com.niit;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;

@WebServlet("/FetchDemo3")
public class FetchDemo3 extends HttpServlet 
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
				
			ApiFuture<QuerySnapshot> query = db.collection("users").whereEqualTo("username", "Govind123").get();

			QuerySnapshot querySnapshot = query.get();
			List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
			
			for (QueryDocumentSnapshot document : documents)
			{
				  User user=document.toObject(User.class);
				  PrintWriter out=resp.getWriter();
				  out.println("Username: "+user.getUsername()+"<br>");
				  out.println("Email: "+user.getEmail()+"<br>");
				  out.println("Mobile: "+user.getMobile()+"<br><hr><br>");
			}
		}
		catch (Exception e) 
		{
			PrintWriter out=resp.getWriter();
			out.println(e+"");
		}
	}

}
