package com.th;

/* in case of intrinsic lock, when exception is raised in a synchronized code, lock is automatically released.
But in case of ReentrantLock when exception is raised , lock is not released.
*/
import java.util.concurrent.locks.*;
public class Reentrant implements Runnable
{
	ReentrantLock mylock=new ReentrantLock();
	public void run()
	{
		mylock.lock();
		for(int i=0;i<5;i++)
		{	
			System.out.println("Hello"+i);
			try
			{
				Thread.sleep(100);
			}
			catch(InterruptedException ie)
			{
				System.out.println(ie);
			}
						if(i==2 && Thread.currentThread().getName().equalsIgnoreCase("first"))
		{
			throw new RuntimeException();
		}
		}
		
		mylock.unlock();
	}
	/*synchronized public void run()
	{
		for(int i=0;i<5;i++)
		{	
			System.out.println("Hello"+i);
			try
			{
				Thread.sleep(100);
			}
			catch(InterruptedException ie)
			{
				System.out.println(ie);
			}
			if(i==2 && Thread.currentThread().getName().equalsIgnoreCase("first"))
		{
			throw new RuntimeException();
		}
		}
		
	}*/
	public static void main(String args[])
	{
		Reentrant ob=new Reentrant();
		Thread t1=new Thread(ob,"first");
		Thread t2=new Thread(ob,"second");
		t1.start();
		t2.start();
	}
}