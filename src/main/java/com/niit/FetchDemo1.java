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

@WebServlet("/FetchDemo1")
public class FetchDemo1 extends HttpServlet 
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
				
			ApiFuture<QuerySnapshot> query = db.collection("users").get();

			QuerySnapshot querySnapshot = query.get();
			List<QueryDocumentSnapshot> documents = querySnapshot.getDocuments();
			for (QueryDocumentSnapshot document : documents)
			{
				  PrintWriter out=resp.getWriter();
				  out.println("User: " + document.getId()+"<br>");
				  out.println("First: " + document.getString("first")+"<br>");
				  if (document.contains("middle")) 
				  {
				    out.println("Middle: " + document.getString("middle")+"<br>");
				  }
				  out.println("Last: " + document.getString("last")+"<br>");
				  out.println("Born: " + document.getLong("born")+"<br><br><hr><br>");
			}
		}
		catch (Exception e) 
		{
			PrintWriter out=resp.getWriter();
			out.println(e+"");
		}
	}
	
}
