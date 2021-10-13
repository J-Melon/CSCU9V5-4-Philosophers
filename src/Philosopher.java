import java.util.concurrent.Semaphore;
import java.util.Random;

public class Philosopher extends Thread
{
	private final int LOOPS = 25;
	private  final int RND_BOUND = 1;
	
	private String name;
	private Semaphore sem1;
	private Semaphore sem2;
	
	private Random rndDuration;
	private Random rndChoice;
	
	public Philosopher(String name, Semaphore sem1, Semaphore sem2)
	{
		this.name = name;
		this.sem1 = sem1;
		this.sem2 = sem2;
		
		rndDuration = new Random();
		rndChoice = new Random();
	}
	
	@Override
	public void run()
	{
		int rndNum;
		
		for (int i = 0; i < LOOPS; i++)
		{
			rndNum = rndChoice.nextInt(2); // Coin flip
			
			// Philosopher thinks about what choice to make
			try {Thread.sleep(rndDuration.nextInt(RND_BOUND));}
			catch (InterruptedException e) { System.err.println("Failed to sleep: " + e); }
			
			// GRAB L OR R
			if (rndNum == 0)
			{
				// GRAB R
				try { sem1.acquire(); }
				catch (InterruptedException e) { System.err.println("[ERROR] Failed to acquire semaphore lock: " + e); }
				System.out.println("[" + name + "] " + "Acquired chopstick R.");
				try {Thread.sleep(rndDuration.nextInt(RND_BOUND));}
				catch (InterruptedException e) { System.err.println("Failed to sleep: " + e); }
			}
			else
			{
				// GRAB L
				try { sem2.acquire(); }
				catch (InterruptedException e) { System.err.println("[ERROR] Failed to acquire semaphore lock: " + e); }
				System.out.println("[" + name + "] " + "Acquired chopstick L.");
				try {Thread.sleep(rndDuration.nextInt(RND_BOUND));}
				catch (InterruptedException e) { System.err.println("Failed to sleep: " + e); }
			}
			
			// THINK
			think();
			try {Thread.sleep(rndDuration.nextInt(RND_BOUND));}
			catch (InterruptedException e) { System.err.println("Failed to sleep: " + e); }
			
			// GRAB L OR R (opposite to first choice)
			if (rndNum == 0)
			{
				// GRAB L
				try { sem2.acquire(); }
				catch (InterruptedException e) { System.err.println("[ERROR] Failed to acquire semaphore lock: " + e); }
				System.out.println("[" + name + "] " + "Acquired chopstick L.");
				try {Thread.sleep(rndDuration.nextInt(RND_BOUND));}
				catch (InterruptedException e) { System.err.println("Failed to sleep: " + e); }
			}
			else
			{
				// GRAB R
				try { sem1.acquire(); }
				catch (InterruptedException e) { System.err.println("[ERROR] Failed to acquire semaphore lock: " + e); }
				System.out.println("[" + name + "] " + "Acquired chopstick R.");
				try {Thread.sleep(rndDuration.nextInt(RND_BOUND));}
				catch (InterruptedException e) { System.err.println("Failed to sleep: " + e); }
			}
			
			// EAT
			System.out.println("[" + name + "] " + "Ate.");
			try {Thread.sleep(rndDuration.nextInt(RND_BOUND));}
			catch (InterruptedException e) { System.err.println("Failed to sleep: " + e); }
			
			// RELEASE L+R (Put down chopsticks after eating)
			sem1.release();
			sem2.release();
			System.out.println("[" + name + "] " + "Released L and R chopsticks.");
			try {Thread.sleep(rndDuration.nextInt(RND_BOUND));}
			catch (InterruptedException e) { System.err.println("Failed to sleep: " + e); }
		}
		
		System.out.println("\n[PHILOSOPHER "+ name +" LEAVES]\n"); // End of meal and execution
	}
	
	public void think()
	{
		Random rnd = new Random();
		
		String[] thoughts = {"Do most people feel they aren't living their full lives?",
				"Can people gain an education without proper schooling?",
				"Why are some people living without actually experiencing things?",
				"What does it mean to be loved or to love others?",
				"Is torture a justifiable form of punishment?",
				"What does it mean to be a postmodern philosopher?",
				"Do we exist in some form after death?",
				"Are we more likely to cause trouble because of boredom?",
				"What are the pros and cons of a communist government?",
				"Are our morals connected to or influenced by culture?",
				"What is the most effective way of increase one’s IQ?",
				"What is death?",
				"Whom is death?",
				"Is sorcery a real thing?",
				"Are people really the loving beings they think they are?",
				"What does self-development mean for me?",
				"What is divinity?",
				"How does autism effect cognitive behavior?",
				"What is good and bad?",
				"What is witchcraft in ancient times?",
				"Where does our conscience go after we die?",
				"What is power?",
				"Does magic exist?",
				"What is the link between a person’s name and his traits?",
				"Who decides what is good and what is bad?",
				"Are people evil or good by nature?",
				"Are there facts that are actually moral?",
				"Do the unborn future generations have any right to affect our decisions today?",
				"Does the level of education alter morality in a positive or negative way?",
				"How can we claim that our current moral standards are universal and perfect, if they are not, are they really universal?",
				"If genetic engineering is used on humans, are they humans anymore?",
				"Are good character and evil character relative or absolute?",
				"Is it ethical and moral to decide and enforce the spaying of animals?",
				"Is it ethical to deny a person an award or recognition of extraordinary feats and skill, if they have low moral standards?",
				"Is it ethical to use embryos in medical and genetic engineering research?",
				"Is there a moral duty to care for the environment?",
				"Should the advancement of technology be slowed to fit with Philosophy?",
				"Should the elderly decide on if they should move to a care center or someone else like a health specialist or their families?",
				"Should the notion of free will be reconsidered?",
				"Are clones individuals or extensions of the original?",
				"Are the philosophy and religion exclusive of each other?",
				"Can the state be allowed to continue to censor the creative work of the artists?",
				"Do countries have any ethical or moral responsibilities to assist with global issues?",
				"Do moral rules limit humans? Can unethical or immoral behavior achieve common good?",
				"Do people suffer by choice?",
				"Does genetics contribute to one's behavior?",
				"How ethical is it to use drugs that enhance mental capabilities?",
				"Is charity an ethical and moral obligation for wealthy people?",
				"Is it always correct to be honest?",
				"Is it ethical to censor hate speech on social media sites?",
				"Is knowledge a result of learning or experience?",
				"Is morality objective or subjective?",
				"Should personal use of drugs be legal as it is a personal choice?",
				"Who watches the watchers, is mass surveillance justified?",
				"Are religions ethical and moral?",
				"Are religions the greatest scams in the world?",
				"Are wars ethical or moral?",
				"Do the ends justify the means?",
				"Do humans have the right to die by choice?",
				"Does ageing alter the way we perceive beauty?",
				"Does money provide happiness or fulfilment?",
				"Does one society have the moral and ethical obligation to enforce its systems to another society?",
				"How can the theory of philosophy be applied to the real-world?",
				"Is it ethical to allow social media to influence our morals?",
				"Is it possible to ever make amendments?",
				"Is morality essential, or is it merely wishful thinking?",
				"Should everyone have the same ideas of right and wrong?",
				"When is, if ever, torture justifiable?",
				"Which one is more valid: Morals or Greater Good?",
				"Can patriotism be considered a moral virtue?",
				"Can people with cognitive or mental disorders be held to the same moral standards?",
				"Can we consider atheism a belief like others?",
				"Do humans have a moral obligation to spread life to the universe?",
				"Is beauty dependent on the beholder, or does universal beauty exist?",
				"Is it moral or ethical to pass beliefs that can't be proven one way or another to children?"
		};
		
		System.out.println("[" + name + "] " + "Thought: " + thoughts[rnd.nextInt(76)]);
	}
}
