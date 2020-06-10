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

@WebServlet("/StoreData")
public class StoreData extends HttpServlet 
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
			
			DocumentReference docRef = db.collection("users").document("aturing");
			
			Map<String, Object> data = new HashMap<>();
			data.put("first", "Alan");
			data.put("middle", "Mathison");
			data.put("last", "Turing");
			data.put("born", 1912);

			
			ApiFuture<WriteResult> result = docRef.set(data);
		}
		catch(Exception e)
		{
			PrintWriter out=resp.getWriter();
			out.println(e+"");
		}
	}
}
