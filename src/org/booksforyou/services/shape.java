package org.booksforyou.services;

 interface shape {
   double area();
  
	
}

 interface quadrilateral extends shape{
	double sidenumber();
}
 
 public class Geometry implements quadrilateral{
	 public double area() {
		 return 0;
	 }
	 public double sidenumber() {
		 return 0;
	 }
 }