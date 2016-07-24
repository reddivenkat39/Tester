package com.Tester;

class Downloadable implements Runnable
{
	int flag=0;
	public void run()
	{
		if(Thread.currentThread().getName().equals("interrupter"))
		{
			this.flag =1;
		}
		
		for(int i=0;i<15;i++)
		{
			if(this.flag==0)
			{
		System.out.println("your file is seeding - seed "+ (i+1)+" "+ Thread.currentThread().getName());
			}
			else
			{
				System.out.println("server stopped");
				break;
			}
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		}
	}
}

class timer implements Runnable
{
	Downloadable db;
	timer(Downloadable db)
	{
		this.db = db;
	}
	int serverstatus = 0;
	public void run()
	{
		for(int i=0;i<5;i++)
		{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		serverstatus =1;
		if(serverstatus == 1)
		{
			Thread interrupter = new Thread(db,"interrupter");
			interrupter.start();
		}
	}
}


public class ClientDownloadSimulation {
public static void main(String[] args)
{
	Downloadable downloadable = new Downloadable();
	timer servertimeout = new timer(downloadable);
	Thread time = new Thread(servertimeout,"Timer");
	time.start();
	Thread user1 = new Thread(downloadable,"user1");
	user1.start();
	try {
		Thread.sleep(1000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	Thread user2 = new Thread(downloadable,"user2");
	user2.start();
	
	// now two Threads are accessing the same resource and after reaching a time That is at the clock time both should stop 
	// with a message server is down
	
	
}
}
