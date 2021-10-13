import java.util.concurrent.Semaphore;

public class PhilosopherProblem
{
	public static void main(String[] args)
	{
		Semaphore sem1 = new Semaphore(1);
		Semaphore sem2 = new Semaphore(1);
		Semaphore sem3 = new Semaphore(1);
		
		// Semaphores make a circle suitable for deadlock (param: R, L)
		Philosopher A = new Philosopher("A", sem1, sem2);
		Philosopher B = new Philosopher("B", sem2, sem3);
		Philosopher C = new Philosopher("C", sem3, sem1);
		
		A.start();
		B.start();
		C.start();
		
	}
}
