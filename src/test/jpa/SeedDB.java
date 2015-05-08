package test.jpa;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;

import core.Celebrity;
import core.Movie;
import core.News;
import core.User;
import dao.DAOCelebrity;
import dao.DAOMovie;
import dao.DAONews;
import dao.DAOUser;
import dao.jpa.DAOCelebrityJPA;
import dao.jpa.DAOMovieJPA;
import dao.jpa.DAONewsJPA;
import dao.jpa.DAOUserJPA;
import dao.jpa.managers.DAOJPAPublished;
import dao.jpa.managers.DAOJPAUnpublished;
import de.svenjacobs.loremipsum.LoremIpsum;

public class SeedDB {
	
	private DAONews daoNews = new DAONewsJPA();
	private DAOUser daoUser = new DAOUserJPA();
	private DAOCelebrity daoCelebrity = new DAOCelebrityJPA();
	private DAOMovie daoMovie = new DAOMovieJPA();
	private User 	adminUser1,proUser1,proUser2;
	private Celebrity clintEastwood,sergioLeone;
	private Movie bonBruteTruand;
	/*
			c2 = new Celebrity("Christoph","Waltz",new LoremIpsum().getWords(250),new Date(1956,10,4),""),
			c3 = new Celebrity("Robert","Downey Jr.",new LoremIpsum().getWords(150),new Date(1965,4,4),""),
			c4 = new Celebrity("Scarlett","Johanson",new LoremIpsum().getWords(350),new Date(1984,11,22),""),
			c5 = new Celebrity("Ethan","Hawke",new LoremIpsum().getWords(154),new Date(1970,11,6),""),
			c6 = new Celebrity("Uma","Thurman",new LoremIpsum().getWords(250),new Date(1970,4,29),""),
			c7 = new Celebrity("George","Lucas",new LoremIpsum().getWords(321),new Date(),""),
			c8 = new Celebrity("Liam","Neeson",new LoremIpsum().getWords(211),new Date(),""),
			c9 = new Celebrity("Ewan","McGregor",new LoremIpsum().getWords(121),new Date(),""),
			c10 = new Celebrity("Carrie","Fisher",new LoremIpsum().getWords(121),new Date(),""),
			c11 = new Celebrity("Jacques","Chirac",new LoremIpsum().getWords(121),new Date(),"");
							
	
	
	Movie 	m1 = new Movie("Star Wars : La menace fantôme", new Date(12,6,1999), 50000000, new LoremIpsum().getWords(150)),
			m2 = new Movie("Harry Potter 1", new Date(12,6,2002), 20000000, new LoremIpsum().getWords(150)),
			m3 = new Movie("Harry Potter 2", new Date(12,6,2004), 50000000, new LoremIpsum().getWords(150)),
			m4 = new Movie("Harry Potter 3", new Date(12,6,2006), 50000000, new LoremIpsum().getWords(150)),
			m5 = new Movie("Harry Potter 4", new Date(12,6,2007), 50000000, new LoremIpsum().getWords(150));
			*/
	
	@org.junit.Test
	public void test() 
	{
		seedDB();
	}
	
	@Before
	public void init() 
	{
		adminUser1 	= new User("admin", "admin", "justinbieber@bieber.com","Justin","Bieber", new java.util.Date(), "",true,true);
		proUser1	= new User("user1","mdp1","a@a.com","Élie","Yaffa",new java.util.Date(),"",false,true);
		proUser2	= new User("user2","mdp2","b@b.com","Kaaris","Sevran",new java.util.Date(),"",false,true);
		
		this.clintEastwood = new Celebrity("Clint","Eastwood","Perhaps the icon of macho movie stars, Clint Eastwood has become a standard in international cinema. He was born in 1930 in San Francisco, to Margaret Ruth (Runner), a factory worker, and Clinton Eastwood, Sr., a steelworker. Eastwood briefly attended Los Angeles City College but dropped out to pursue acting. He found bit work in such B-films as La revanche de la créature (1955) and Tarantula (1955) until he got his first breakthrough in the long-running TV series Rawhide (1959). As Rowdy Yates, he made the show his own and became a household name around the country. "
				+ "Eastwood found bigger and better things in Italy with the spaghetti westerns Pour une poignée de dollars (1964) and Et pour quelques dollars de plus (1965), but it was the third installment in the trilogy where he found one of his signature roles: Le bon, la brute et le truand (1966). The movie was a big hit and brought him instant international recognition. He followed it up with his first American-made western, Pendez-les haut et court (1968), before playing second fiddle to Richard Burton in the World War II epic Quand les aigles attaquent (1968) and Lee Marvin in the unusual musical La kermesse de l'Ouest (1969). In Sierra torride (1970) and De l'or pour les braves (1970), Eastwood combined tough-guy action with offbeat humor."
				+ "1971 proved to be one of his best years in film, if not the best. He starred in Les proies (1971) and the cult classic Un frisson dans la nuit (1971). But it was his role as the hard edge police inspector in L'inspecteur Harry (1971) that elevated Eastwood to superstar status and invented the loose-cannon cop genre that has been imitated even to this day. Eastwood had constant quality films over the following years with Le canardeur (1974) opposite Jeff Bridges, the Dirty Harry sequels Magnum Force (1973) and L'inspecteur ne renonce jamais (1976), the westerns Joe Kidd (1972), L'homme des hautes plaines (1973) and Josey Wales hors-la-loi (1976), and the fact-based thriller L'évadé d'Alcatraz (1979). In 1978 Eastwood branched out into the comedy genre with Doux, dur et dingue (1978), which became the biggest hit of his career up to that time. Taking inflation into account, it still is."
				+ "Eastwood kicked off the eighties with Ça va cogner (1980), the blockbuster sequel to Every Which Way But Loose. The fourth Dirty Harry film, Sudden Impact - Le retour de l'inspecteur Harry (1983), was the highest-grossing film of the franchise and spawned Eastwood's trademark catchphrase, \"Make my day\". Eastwood also starred in Firefox, l'arme absolue (1982), La corde raide (1984), Haut les flingues! (1984) (with Burt Reynolds), Pale Rider - Le cavalier solitaire (1985), and Le maître de guerre (1986), which were all big hits. In 1988 Eastwood did his fifth and final Dirty Harry movie, L'inspecteur Harry est la dernière cible (1988). Although it was a success overall, it did not have the box office punch his previous films had. Shortly thereafter, with outright bombs like Pink Cadillac (1989) and La relève (1990), it became apparent that Eastwood's star was declining as it never had before. He then started taking on more personal projects, such as directing Bird (1988), a biopic of Charlie Parker, and starring in and directing Chasseur blanc, coeur noir (1990), an uneven, loose biopic of John Huston."
				+ "But Eastwood bounced back, first with his western, Impitoyable (1992), which garnered him an Oscar for Best Director, and a nomination for Best Actor. Then he took on the secret service in Dans la ligne de mire (1993), which was a big hit, followed by the interesting but poorly received drama, Un monde parfait (1993), with Kevin Costner. Next up was a love story, Sur la route de Madison (1995), which was yet again a success. Eastwood's subsequent films were solid but nothing really stuck out. Among them were the well-received Les pleins pouvoirs (1997) and Space Cowboys (2000), and the badly received Jugé coupable (1999) and Créance de sang (2002). Then in 2004, Eastwood surprised yet again when he produced, directed and starred in Million Dollar Baby (2004). The movie earned Eastwood an Oscar for Best Director and a Best Actor nomination for the second time. He had other successes directing the multi-award-winning films Mystic River (2003), Mémoires de nos pères (2006), Lettres d'Iwo Jima (2006), and L'échange (2008) which starred Angelina Jolie. After a four-year hiatus from acting, Eastwood's return to the screen in Gran Torino (2008) gave him a $30 million opening weekend, proving his box office appeal has not waned."
				+ "Eastwood has managed to keep his extremely complicated personal life private and has rarely been featured in the tabloids. He had a long time relationship with frequent co-star Sondra Locke and has eight children by six other women, only two of whom he married. Eastwood divides his time between Carmel and Los Angeles.",
				"31/05/1930","");
		
		this.sergioLeone = new Celebrity("Sergio","Leone","Sergio Leone was virtually born into the cinema - he was the son of Roberto Roberti (A.K.A. Vincenzo Leone), one of Italy's cinema pioneers, and actress Bice Valerian. Leone entered films in his late teens, working as an assistant director to both Italian directors and U.S. directors working in Italy.","03/01/1929","");
		
		this.bonBruteTruand = new Movie("Le bon, la brute et le truand", "01/01/1966", 1200000, "The film tells the story of three men who pursue, often at the expense of others, information about the location of a buried treasure of coins. The first character introduced in the movie is Tuco Benedicto Pacifico Juan Maria Ramirez (the Ugly) - called Tuco - (Eli Wallach), who has a bounty on his head for numerous crimes. Tuco has a partnership with Blondie (The Good, played by Clint Eastwood) in which the latter turns him in for the reward money which the two then split after Blondie saves Tuco from hanging at the last moment. Meanwhile, a third character called Angel Eyes (Lee Van Cleef, playing the Bad) has learned of a hidden trunk of gold owned by a Confederate soldier named Bill Carson. He sets off to find the gold.");
		this.bonBruteTruand.addActor(clintEastwood);
		this.bonBruteTruand.setDirector(sergioLeone);
		
		try {
			daoCelebrity.save(this.clintEastwood);
			daoCelebrity.save(this.sergioLeone);
			daoMovie.save(this.bonBruteTruand);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public void seedDB()
	{
		DAOJPAPublished.viderBase();
		init();
		seedUsers();
		/*
		seedCelebrities();
		seedNews();
		seedMovies();
		*/
	}
	
	public void seedUsers()
	{
		adminUser1 	= new User("admin", "admin", "justinbieber@bieber.com","Justin","Bieber", new java.util.Date(), "",true,true);
		proUser1	= new User("user1","mdp1","a@a.com","Élie","Yaffa",new java.util.Date(),"",false,true);
		proUser2	= new User("user2","mdp2","b@b.com","Kaaris","Sevran",new java.util.Date(),"",false,true);
		daoUser.save(adminUser1);
		daoUser.save(proUser1);
		daoUser.save(proUser2);
	}
	
	public void seedNews()
	{
		for (int i = 1 ; i <= 5 ; i++)
		{
			daoNews.save(new News(adminUser1,"title"+i,"content"+i,"",new Date()));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
			
	}
	
	public void seedCelebrities()
	{	
		try {
			/*
			daoCelebrity.save(c1);
			daoCelebrity.save(c2);
			daoCelebrity.save(c3);
			daoCelebrity.save(c4);
			daoCelebrity.save(c5);
			daoCelebrity.save(c6);
			daoCelebrity.save(c7);
			*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void seedMovies()
	{
		/*
		m1.setDirector(c7);
		m1.addActor(c8);
		m1.addActor(c9);
		m1.addActor(c10);
		m1.addProducer(c11);*/
		try {
			/*
			daoMovie.save(m1);
			daoMovie.save(m2);
			daoMovie.save(m3);
			daoMovie.save(m4);
			daoMovie.save(m5);
			*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
