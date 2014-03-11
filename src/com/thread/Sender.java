package com.thread;

import com.net.SendGetRequest;
import com.str.ResponseCutter;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by Edward on 14-1-20.
 */
public class Sender implements Runnable {
	// set request get
	private CyclicBarrier barrier;

	private static String head = "/fcgi-bin/PCoreService.fcg?Method=Register";

	public Sender(CyclicBarrier cb, String ip, int thr, String tg, int looper) {
		this.barrier = cb;
		// 0:ip,1:threads,2:TgMark
		// set threads
		// set TgMark
		while (looper > 1) {
			String Ip = ip;
			String TgMark = tg;
			String IDNumber;
			int Loops = 300 + looper;
			int threads = Loops * 10000 + thr;
			// set IDNumber
			IDNumber = TgMark + Loops + "0000000" + threads;
			// set UserName
			String UserName = "bbbb" + threads + tg;
			// set Password
			String Password = "abcd1234haha";
			// set TrueName
			String TrueName = "bbbb" + threads + tg + "b";
			// set Email
			String Email = UserName + "@www.com";
			// set MobilePhone
			String MobilePhone = TgMark + Loops + threads;
			// set Agencyid
			String Agencyid = "123";

			// url
			String url = "http://" + Ip + head + "&Password=" + Password
					+ "&UserName=" + UserName + "&IDNumber=" + IDNumber
					+ "&TrueName=" + TrueName + "&Email=" + Email
					+ "&MobilePhone=" + MobilePhone + "&Agencyid=" + Agencyid;

			try {
				String returned = SendGetRequest.SendUrlRequest(url);
				String Code = ResponseCutter.HttpResponseCutter(returned);
				if (!Code.equals("00000")) {
					System.out.println("error thread group:" + TgMark
							+ " error thread id:" + threads);
					System.out.println("error code:" + Code);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
			looper--;
		}
	}

	@Override
	public void run() {
		try {
			barrier.await();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
