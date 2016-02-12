package com.example.williamtygret.pokedex;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by williamtygret on 2/5/16.
 */
public class PokemonDatabaseHelper extends SQLiteOpenHelper {

    //created all necessary columns for my db
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "PokemonLibrary.db";
    public static final String POKEMON_TABLE_NAME = "POKEMON_LIBRARY";

    public static final String COL_ID = "_id";
    public static final String COL_POKEMON_NAME = "POKEMON_NAME";
    public static final String COL_POKEMON_DESCRIPTION = "DESCRIPTION";
    public static final String COL_POKEMON_HP = "HP";
    public static final String COL_POKEMON_TYPE = "TYPE";
    public static final String COL_IS_FAVORITE = "FAVORITE";
    public static final String COL_IMG = "IMG";

    private Context mHelperContext;

    public static final String [] POKEMON_COLUMNS = {COL_ID,COL_POKEMON_NAME,COL_POKEMON_DESCRIPTION,COL_POKEMON_HP,COL_POKEMON_TYPE,COL_IS_FAVORITE,COL_IMG};

    //creating tables up in here
    private static final String CREATE_POKEMON_TABLE =
            "CREATE TABLE "+ POKEMON_TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                    COL_POKEMON_NAME + " TEXT, "+
                    COL_POKEMON_DESCRIPTION + " TEXT, "+
                    COL_POKEMON_HP + " TEXT, "+
                    COL_POKEMON_TYPE + " TEXT, "+
                    COL_IS_FAVORITE + " TEXT, "+
                    COL_IMG + " TEXT )";



    //making my database accessible in a singleton
    private static PokemonDatabaseHelper instance;

    public static PokemonDatabaseHelper getInstance(Context context){
        if(instance == null){
            instance = new PokemonDatabaseHelper(context);
        }
        return instance;
    }

    private PokemonDatabaseHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        mHelperContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_POKEMON_TABLE);
        Log.d("PokemonDatabaseHelper", "SQL" + CREATE_POKEMON_TABLE);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//upgrades the current database
        db.execSQL("DROP TABLE IF EXISTS pokemon");
        onCreate(db);
    }

    public Cursor getPokemonList(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(POKEMON_TABLE_NAME, // a. table
                POKEMON_COLUMNS, // b. column names
                null, // c. selections
                null, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        return cursor;
    }

    //method to insert poke data
    public void insertPokemon(int id, String name, String description, String hp, String type, int favorite, String img){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("_id", id);
        values.put("POKEMON_NAME", name);
        values.put("DESCRIPTION", description);
        values.put("HP", hp);
        values.put("TYPE", type);
        values.put("FAVORITE" , favorite);
        values.put("IMG", img);

        db.insert("POKEMON_LIBRARY", null, values);
        Log.d("PokemonDatabaseHelper","insert being called from insertPokemon");
    }

    //the trillest database on earth!!!!
    public void insertAllPokemon(){
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(1, "Bulbasaur","Bulbasaur can be seen napping in bright sunlight. There is a seed on its back. By soaking up the sun's rays, the seed grows progressively larger.","40","Grass",0,"001");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(2, "Ivysaur","There is a bud on this Pokémon's back. To support its weight, Ivysaur's legs and trunk grow thick and strong. If it starts spending more time lying in the sunlight, it's a sign that the bud will bloom into a large flower soon.","60","Grass",0,"002");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(3, "Venusaur","There is a large flower on Venusaur's back. The flower is said to take on vivid colors if it gets plenty of nutrition and sunlight. The flower's aroma soothes the emotions of people.","100","Grass",0,"003");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(4, "Charmander","The flame that burns at the tip of its tail is an indication of its emotions. The flame wavers when Charmander is enjoying itself. If the Pokémon becomes enraged, the flame burns fiercely.","50","Fire",0,"004");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(5, "Charmeleon","Charmeleon mercilessly destroys its foes using its sharp claws. If it encounters a strong foe, it turns aggressive. In this excited state, the flame at the tip of its tail flares with a bluish white color.","80","Fire",0,"005");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(6, "Charizard","Charizard flies around the sky in search of powerful opponents. It breathes fire of such great heat that it melts anything. However, it never turns its fiery breath on any opponent weaker than itself.","120","Fire",0,"006");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(7, "Squirtle","Squirtle's shell is not merely used for protection. The shell's rounded shape and the grooves on its surface help minimize resistance in water, enabling this Pokémon to swim at high speeds.","50","Water",0,"007");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(8, "Wartortle","Its tail is large and covered with a rich, thick fur. The tail becomes increasingly deeper in color as Wartortle ages. The scratches on its shell are evidence of this Pokémon's toughness as a battler.","70","Water",0,"008");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(9, "Blastoise","Blastoise has water spouts that protrude from its shell. The water spouts are very accurate. They can shoot bullets of water with enough accuracy to strike empty cans from a distance of over 160 feet.","100","Water",0,"009");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(10, "Caterpie","Caterpie has a voracious appetite. It can devour leaves bigger than its body right before your eyes. From its antenna, this Pokémon releases a terrifically strong odor.","40","Bug",0,"010");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(11, "Metapod","The shell covering this Pokémon's body is as hard as an iron slab. Metapod does not move very much. It stays still because it is preparing its soft innards for evolution inside the hard shell.","70","Bug",0,"011");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(12, "Butterfree","Butterfree has a superior ability to search for delicious honey from flowers. It can even search out, extract, and carry honey from flowers that are blooming over six miles from its nest.","70","Bug",0,"012");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(13, "Weedle","Weedle has an extremely acute sense of smell. It is capable of distinguishing its favorite kinds of leaves from those it dislikes just by sniffing with its big red proboscis (nose).","40","Bug",0,"013");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(14, "Kakuna","Kakuna remains virtually immobile as it clings to a tree. However, on the inside, it is extremely busy as it prepares for its coming evolution. This is evident from how hot the shell becomes to the touch.","80","Bug",0,"014");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(15, "Beedrill","Beedrill is extremely territorial. No one should ever approach its nest—this is for their own safety. If angered, they will attack in a furious swarm.","80","Bug",0,"015");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(16, "Pidgey","Squirtle's shell is not merely used for protection. The shell's rounded shape and the grooves on its surface help minimize resistance in water, enabling this Pokémon to swim at high speeds.","40","Flying",0,"016");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(17, "Pidgeotto","Pidgeotto claims a large area as its own territory. This Pokémon flies around, patrolling its living space. If its territory is violated, it shows no mercy in thoroughly punishing the foe with its sharp claws.","60","Flying",0,"017");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(18, "Pidgeot","This Pokémon has a dazzling plumage of beautifully glossy feathers. Many Trainers are captivated by the striking beauty of the feathers on its head, compelling them to choose Pidgeot as their Pokémon.","80","Flying",0,"018");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(19, "Rattata","Rattata is cautious in the extreme. Even while it is asleep, it constantly listens by moving its ears around. It is not picky about where it lives—it will make its nest anywhere.","30","Normal",0,"019");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(20, "Raticate","Raticate's sturdy fangs grow steadily. To keep them ground down, it gnaws on rocks and logs. It may even chew on the walls of houses.","60","Normal",0,"020");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(21, "Spearow","Spearow has a very loud cry that can be heard over half a mile away. If its high, keening cry is heard echoing all around, it is a sign that they are warning each other of danger.","50","Flying",0,"021");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(22, "Fearow","Fearow is recognized by its long neck and elongated beak. They are conveniently shaped for catching prey in soil or water. It deftly moves its long and skinny beak to pluck prey.","70","Flying",0,"022");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(23, "Ekans","Ekans curls itself up in a spiral while it rests. Assuming this position allows it to quickly respond to a threat from any direction with a glare from its upraised head.","40","Poison",0,"023");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(24, "Arbok","This Pokémon is terrifically strong in order to constrict things with its body. It can even flatten steel oil drums. Once Arbok wraps its body around its foe, escaping its crunching embrace is impossible.","60","Poison",0,"024");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(25, "Pikachu","Whenever Pikachu comes across something new, it blasts it with a jolt of electricity. If you come across a blackened berry, it's evidence that this Pokémon mistook the intensity of its charge.","40","Electric",0,"025");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(26, "Raichu","If the electrical sacs become excessively charged, Raichu plants its tail in the ground and discharges. Scorched patches of ground will be found near this Pokémon's nest.","80","Electric",0,"026");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(27, "Sandshrew","Sandshrew's body is configured to absorb water without waste, enabling it to survive in an arid desert. This Pokémon curls up to protect itself from its enemies.","40","Ground",0,"027");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(28, "Sandslash","Sandslash's body is covered by tough spikes, which are hardened sections of its hide. Once a year, the old spikes fall out, to be replaced with new spikes that grow out from beneath the old ones.","70","Ground",0,"028");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(29, "Nidoran (Female)","Nidoran has barbs that secrete a powerful poison. They are thought to have developed as protection for this small-bodied Pokémon. When enraged, it releases a horrible toxin from its horn.","60","Poison",0,"029");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(30, "Nidorina","When Nidorina are with their friends or family, they keep their barbs tucked away to prevent hurting each other. This Pokémon appears to become nervous if separated from the others.","70","Poison",0,"030");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(31, "Nodoqueen","Nidoqueen's body is encased in extremely hard scales. It is adept at sending foes flying with harsh tackles. This Pokémon is at its strongest when it is defending its young.","90","Poison",0,"031");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(32, "Nidoran (Male)","Nidoran has developed muscles for moving its ears. Thanks to them, the ears can be freely moved in any direction. Even the slightest sound does not escape this Pokémon's notice.","40","Poison",0,"032");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(33, "Nidorino","Nidorino has a horn that is harder than a diamond. If it senses a hostile presence, all the barbs on its back bristle up at once, and it challenges the foe with all its might.","60","Poison",0,"033");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(34, "Nidoking","Nidoking's thick tail packs enormously destructive power. With one swing, it can topple a metal transmission tower. Once this Pokémon goes on a rampage, there is no stopping it.","90","Poison",0,"034");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(35, "Clefairy","On every night of a full moon, groups of this Pokémon come out to play. When dawn arrives, the tired Clefairy return to their quiet mountain retreats and go to sleep nestled up against each other.","40","Fairy",0,"035");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(36, "Clefable","Clefable moves by skipping lightly as if it were flying using its wings. Its bouncy step lets it even walk on water. It is known to take strolls on lakes on quiet, moonlit nights.","70","Fairy",0,"036");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(37, "Vulpix","At the time of its birth, Vulpix has one white tail. The tail separates into six if this Pokémon receives plenty of love from its Trainer. The six tails become magnificently curled.","50","Fire",0,"037");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(38, "Ninetails","Ninetales casts a sinister light from its bright red eyes to gain total control over its foe's mind. This Pokémon is said to live for a thousand years.","80","Fire",0,"038");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(39, "Jigglypuff","Jigglypuff's vocal cords can freely adjust the wavelength of its voice. This Pokémon uses this ability to sing at precisely the right wavelength to make its foes most drowsy.","50","Fairy",0,"039");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(40, "Wigglytuff","Wigglytuff has large, saucerlike eyes. The surfaces of its eyes are always covered with a thin layer of tears. If any dust gets in this Pokémon's eyes, it is quickly washed away.","80","Fairy",0,"040");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(41, "Zubat","Zubat remains quietly unmoving in a dark spot during the bright daylight hours. It does so because prolonged exposure to the sun causes its body to become slightly burned.","40","Poison",0,"041");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(42, "Golbat","Golbat loves to drink the blood of living things. It is particularly active in the pitch black of night. This Pokémon flits around in the night skies, seeking fresh blood.","60","Poison",0,"042");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(43, "Oddish","During the daytime, Oddish buries itself in soil to absorb nutrients from the ground using its entire body. The more fertile the soil, the glossier its leaves become.","50","Grass",0,"043");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(44, "Gloom","Gloom releases a foul fragrance from the pistil of its flower. When faced with danger, the stench worsens. If this Pokémon is feeling calm and secure, it does not release its usual stinky aroma.","60","Grass",0,"044");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(45, "Vileplume","Vileplume's toxic pollen triggers atrocious allergy attacks. That's why it is advisable never to approach any attractive flowers in a jungle, however pretty they may be.","80","Grass",0,"045");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(46, "Paras","Paras has parasitic mushrooms growing on its back called tochukaso. They grow large by drawing nutrients from this Bug Pokémon host. They are highly valued as a medicine for extending life.","40","Grass",0,"046");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(47, "Parasect","Parasect is known to infest large trees en masse and drain nutrients from the lower trunk and roots. When an infested tree dies, they move onto another tree all at once.","60","Grass",0,"047");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(48, "Venonat","Venonat is said to have evolved with a coat of thin, stiff hair that covers its entire body for protection. It possesses large eyes that never fail to spot even minuscule prey.","40","Bug",0,"048");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(49, "Venomoth","Venomoth is nocturnal—it is a Pokémon that only becomes active at night. Its favorite prey are small insects that gather around streetlights, attracted by the light in the darkness.","70","Bug",0,"049");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(50, "Diglett","Diglett are raised in most farms. The reason is simple— wherever this Pokémon burrows, the soil is left perfectly tilled for planting crops. This soil is made ideal for growing delicious vegetables.","30","Ground",0,"050");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(51, "Dugtrio","Dugtrio are actually triplets that emerged from one body. As a result, each triplet thinks exactly like the other two triplets. They work cooperatively to burrow endlessly.","70","Ground",0,"051");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(52, "Meowth","Meowth withdraws its sharp claws into its paws to slinkily sneak about without making any incriminating footsteps. For some reason, this Pokémon loves shiny coins that glitter with light.","50","Normal",0,"052");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(53, "Persian","Persian has six bold whiskers that give it a look of toughness. The whiskers sense air movements to determine what is in the Pokémon's surrounding vicinity. It becomes docile if grabbed by the whiskers.","70","Normal",0,"053");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(54, "Psyduck","Psyduck uses a mysterious power. When it does so, this Pokémon generates brain waves that are supposedly only seen in sleepers. This discovery spurred controversy among scholars.","50","Water",0,"054");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(55, "Golduck","The webbed flippers on its forelegs and hind legs and the streamlined body of Golduck give it frightening speed. This Pokémon is definitely much faster than even the most athletic swimmer.","70","Water",0,"055");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(56, "Mankey","When Mankey starts shaking and its nasal breathing turns rough, it's a sure sign that it is becoming angry. However, because it goes into a towering rage almost instantly, it is impossible for anyone to flee its wrath.","30","Fighting",0,"056");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(57, "Primeape","When Primeape becomes furious, its blood circulation is boosted. In turn, its muscles are made even stronger. However, it also becomes much less intelligent at the same time.","70","Fighting",0,"057");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(58, "Growlithe","Growlithe has a superb sense of smell. Once it smells anything, this Pokémon won't forget the scent, no matter what. It uses its advanced olfactory sense to determine the emotions of other living things.","60","Fire",0,"058");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(59, "Arcanine","Arcanine is known for its high speed. It is said to be capable of running over 6,200 miles in a single day and night. The fire that blazes wildly within this Pokémon's body is its source of power.","100","Fire",0,"059");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(60, "Poliwag","Poliwag has a very thin skin. It is possible to see the Pokémon's spiral innards right through the skin. Despite its thinness, however, the skin is also very flexible. Even sharp fangs bounce right off it.","40","Water",0,"060");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(61, "Poliwhirl","The surface of Poliwhirl's body is always wet and slick with a slimy fluid. Because of this slippery covering, it can easily slip and slide out of the clutches of any enemy in battle.","60","Water",0,"061");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(62, "Poliwrath","Poliwrath's highly developed, brawny muscles never grow fatigued, however much it exercises. It is so tirelessly strong, this Pokémon can swim back and forth across the ocean without effort.","90","Water",0,"062");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(63, "Abra","Abra sleeps for eighteen hours a day. However, it can sense the presence of foes even while it is sleeping. In such a situation, this Pokémon immediately teleports to safety.","30","Psychic",0,"063");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(64, "Kadabra","Kadabra emits a peculiar alpha wave if it develops a headache. Only those people with a particularly strong psyche can hope to become a Trainer of this Pokémon.","60","Psychic",0,"064");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(65, "Alakazam","Alakazam's brain continually grows, making its head far too heavy to support with its neck. This Pokémon holds its head up using its psychokinetic power instead.","80","Psychic",0,"065");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(66, "Machop","Machop's muscles are special—they never get sore no matter how much they are used in exercise. This Pokémon has sufficient power to hurl a hundred adult humans.","50","Fighting",0,"066");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(67, "Machoke","Machoke's thoroughly toned muscles possess the hardness of steel. This Pokémon has so much strength, it can easily hold aloft a sumo wrestler on just one finger.","80","Fighting",0,"067");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(68, "Machamp","Machamp has the power to hurl anything aside. However, trying to do any work requiring care and dexterity causes its arms to get tangled. This Pokémon tends to leap into action before it thinks.","100","Fighting",0,"068");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(69, "Bellsprout","Bellsprout's thin and flexible body lets it bend and sway to avoid any attack, however strong it may be. From its mouth, this Pokémon spits a corrosive fluid that melts even iron.","40","Grass",0,"069");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(70, "Weepinbell","Weepinbell has a large hook on its rear end. At night, the Pokémon hooks on to a tree branch and goes to sleep. If it moves around in its sleep, it may wake up to find itself on the ground.","70","Grass",0,"070");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(71, "Victreebell","Victreebel has a long vine that extends from its head. This vine is waved and flicked about as if it were an animal to attract prey. When an unsuspecting prey draws near, this Pokémon swallows it whole.","80","Grass",0,"071");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(72, "Tentacool","Tentacool's body is largely composed of water. If it is removed from the sea, it dries up like parchment. If this Pokémon happens to become dehydrated, put it back into the sea.","30","Water",0,"072");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(73, "Tentacruel","Tentacruel has large red orbs on its head. The orbs glow before lashing the vicinity with a harsh ultrasonic blast. This Pokémon's outburst creates rough waves around it.","60","Water",0,"073");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(74, "Geodude","The longer a Geodude lives, the more its edges are chipped and worn away, making it more rounded in appearance. However, this Pokémon's heart will remain hard, craggy, and rough always.","50","Rock",0,"074");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(75, "Graveler","Graveler grows by feeding on rocks. Apparently, it prefers to eat rocks that are covered in moss. This Pokémon eats its way through a ton of rocks on a daily basis.","60","Rock",0,"075");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(76, "Golem","Golem live up on mountains. If there is a large earthquake, these Pokémon will come rolling down off the mountains en masse to the foothills below.","80","Rock",0,"076");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(77, "Ponyta","Ponyta is very weak at birth. It can barely stand up. This Pokémon becomes stronger by stumbling and falling to keep up with its parent.","40","Fire",0,"077");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(78, "Rapidash","Rapidash usually can be seen casually cantering in the fields and plains. However, when this Pokémon turns serious, its fiery manes flare and blaze as it gallops its way up to 150 mph.","70","Fire",0,"078");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(79, "Slowpoke","Slowpoke uses its tail to catch prey by dipping it in water at the side of a river. However, this Pokémon often forgets what it's doing and often spends entire days just loafing at water's edge.","50","Psychic",0,"079");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(80, "Slowbro","Slowbro's tail has a Shellder firmly attached with a bite. As a result, the tail can't be used for fishing anymore. This causes Slowbro to grudgingly swim and catch prey instead.","60","Psychic",0,"080");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(81, "Magnemite","Magnemite attaches itself to power lines to feed on electricity. If your house has a power outage, check your circuit breakers. You may find a large number of this Pokémon clinging to the breaker box.","40","Electric",0,"081");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(82, "Magneton","Magneton emits a powerful magnetic force that is fatal to mechanical devices. As a result, large cities sound sirens to warn citizens of large-scale outbreaks of this Pokémon.","70","Electric",0,"082");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(83, "Farfetch'd","Farfetch'd is always seen with a stalk from a plant of some sort. Apparently, there are good stalks and bad stalks. This Pokémon has been known to fight with others over stalks.","50","Flying",0,"083");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(84, "Doduo","Doduo's two heads never sleep at the same time. Its two heads take turns sleeping, so one head can always keep watch for enemies while the other one sleeps.","50","Flying",0,"084");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(85, "Dodrio","Watch out if Dodrio's three heads are looking in three separate directions. It's a sure sign that it is on its guard. Don't go near this Pokémon if it's being wary—it may decide to peck you.","80","Flying",0,"085");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(86, "Seel","Seel hunts for prey in the frigid sea underneath sheets of ice. When it needs to breathe, it punches a hole through the ice with the sharply protruding section of its head.","60","Water",0,"086");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(87, "Dewgong","Dewgong loves to snooze on bitterly cold ice. The sight of this Pokémon sleeping on a glacier was mistakenly thought to be a mermaid by a mariner long ago.","80","Water",0,"087");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(88, "Grimer","Grimer's sludgy and rubbery body can be forced through any opening, however small it may be. This Pokémon enters sewer pipes to drink filthy wastewater.","50","Poison",0,"088");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(89, "Muk","From Muk's body seeps a foul fluid that gives off a nose-bendingly horrible stench. Just one drop of this Pokémon's body fluid can turn a pool stagnant and rancid.","70","Poison",0,"089");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(90, "Shellder","At night, this Pokémon uses its broad tongue to burrow a hole in the seafloor sand and then sleep in it. While it is sleeping, Shellder closes its shell, but leaves its tongue hanging out.","30","Water",0,"090");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(91, "Cloyster","Cloyster is capable of swimming in the sea. It does so by swallowing water, then jetting it out toward the rear. This Pokémon shoots spikes from its shell using the same system.","80","Water",0,"091");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(92, "Ghastly","Gastly is largely composed of gaseous matter. When exposed to a strong wind, the gaseous body quickly dwindles away. Groups of this Pokémon cluster under the eaves of houses to escape the ravages of wind.","30","Ghost",0,"092");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(93, "Haunter","Haunter is a dangerous Pokémon. If one beckons you while floating in darkness, you must never approach it. This Pokémon will try to lick you with its tongue and steal your life away.","60","Ghost",0,"093");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(94, "Gengar","Sometimes, on a dark night, your shadow thrown by a streetlight will suddenly and startlingly overtake you. It is actually a Gengar running past you, pretending to be your shadow.","80","Ghost",0,"094");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(95, "Onix","Onix has a magnet in its brain. It acts as a compass so that this Pokémon does not lose direction while it is tunneling. As it grows older, its body becomes increasingly rounder and smoother.","90","Rock",0,"095");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(96, "Drowzee","If your nose becomes itchy while you are sleeping, it's a sure sign that one of these Pokémon is standing above your pillow and trying to eat your dream through your nostrils.","50","Psychic",0,"096");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(97, "Hypno","Hypno holds a pendulum in its hand. The arcing movement and glitter of the pendulum lull the foe into a deep state of hypnosis. While this Pokémon searches for prey, it polishes the pendulum.","90","Psychic",0,"097");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(98, "Krabby","Krabby live on beaches, burrowed inside holes dug into the sand. On sandy beaches with little in the way of food, these Pokémon can be seen squabbling with each other over territory.","50","Water",0,"098");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(99, "Kingler","Kingler has an enormous, oversized claw. It waves this huge claw in the air to communicate with others. However, because the claw is so heavy, the Pokémon quickly tires.","60","Water",0,"099");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(100, "Voltorb","Voltorb was first sighted at a company that manufactures Poké Balls. The link between that sighting and the fact that this Pokémon looks very similar to a Poké Ball remains a mystery.","40","Electric",0,"100");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(101, "Electrode","Electrode eats electricity in the atmosphere. On days when lightning strikes, you can see this Pokémon exploding all over the place from eating too much electricity.","80","Electric",0,"101");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(102, "Exeggcute","This Pokémon consists of six eggs that form a closely knit cluster. The six eggs attract each other and spin around. When cracks increasingly appear on the eggs, Exeggcute is close to evolution.","50","Grass",0,"102");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(103, "Exeggutor","Exeggutor originally came from the tropics. Its heads steadily grow larger from exposure to strong sunlight. It is said that when the heads fall off, they group together to form Exeggcute.","80","Grass",0,"103");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(104, "Cubone","Cubone pines for the mother it will never see again. Seeing a likeness of its mother in the full moon, it cries. The stains on the skull the Pokémon wears are made by the tears it sheds.","40","Ground",0,"104");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(105, "Marowack","Marowak is the evolved form of a Cubone that has overcome its sadness at the loss of its mother and grown tough. This Pokémon's tempered and hardened spirit is not easily broken.","60","Ground",0,"105");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(106, "Hitmonlee","Hitmonlee's legs freely contract and stretch. Using these springlike legs, it bowls over foes with devastating kicks. After battle, it rubs down its legs and loosens the muscles to overcome fatigue.","60","Fighting",0,"106");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(107, "Hitmonchan","Hitmonchan is said to possess the spirit of a boxer who had been working toward a world championship. This Pokémon has an indomitable spirit and will never give up in the face of adversity.","70","Fighting",0,"107");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(108, "Lickitung","Whenever Lickitung comes across something new, it will unfailingly give it a lick. It does so because it memorizes things by texture and by taste. It is somewhat put off by sour things.","90","Normal",0,"108");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(109, "Koffing","If Koffing becomes agitated, it raises the toxicity of its internal gases and jets them out from all over its body. This Pokémon may also overinflate its round body, then explode.","50","Poison",0,"109");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(110, "Weezing","Weezing loves the gases given off by rotted kitchen garbage. This Pokémon will find a dirty, unkempt house and make it its home. At night, when the people in the house are asleep, it will go through the trash.","60","Poison",0,"110");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(111, "Rhyhorn","Rhyhorn runs in a straight line, smashing everything in its path. It is not bothered even if it rushes headlong into a block of steel. This Pokémon may feel some pain from the collision the next day, however.","70","Rock",0,"111");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(112, "Rhydon","Rhydon's horn can crush even uncut diamonds. One sweeping blow of its tail can topple a building. This Pokémon's hide is extremely tough. Even direct cannon hits don't leave a scratch.","100","Rock",0,"112");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(113, "Chansey","Chansey lays nutritionally excellent eggs on an everyday basis. The eggs are so delicious, they are easily and eagerly devoured by even those people who have lost their appetite.","120","Normal",0,"113");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(114, "Tangela","Tangela's vines snap off easily if they are grabbed. This happens without pain, allowing it to make a quick getaway. The lost vines are replaced by newly grown vines the very next day.","50","Grass",0,"114");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(115, "Kangaskhan","If you come across a young Kangaskhan playing by itself, you must never disturb it or attempt to catch it. The baby Pokémon's parent is sure to be in the area, and it will become violently enraged at you.","90","Normal",0,"115");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(116, "Horsea","Horsea eats small insects and moss off of rocks. If the ocean current turns fast, this Pokémon anchors itself by wrapping its tail around rocks or coral to prevent being washed away.","40","Water",0,"116");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(117, "Seadra","Seadra sleeps after wriggling itself between the branches of coral. Those trying to harvest coral are occasionally stung by this Pokémon's poison barbs if they fail to notice it.","80","Water",0,"117");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(118, "Goldeen","Goldeen is a very beautiful Pokémon with fins that billow elegantly in water. However, don't let your guard down around this Pokémon—it could ram you powerfully with its horn.","40","Water",0,"118");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(119, "Seaking","In the autumn, Seaking males can be seen performing courtship dances in riverbeds to woo females. During this season, this Pokémon's body coloration is at its most beautiful.","70","Water",0,"119");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(120, "Staryu","Staryu's center section has an organ called the core that shines bright red. If you go to a beach toward the end of summer, the glowing cores of these Pokémon look like the stars in the sky.","40","Water",0,"120");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(121, "Starmie","Starmie's center section—the core—glows brightly in seven colors. Because of its luminous nature, this Pokémon has been given the nickname “the gem of the sea.","60","Water",0,"121");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(122, "Mr. Mime","Mr. Mime is a master of pantomime. Its gestures and motions convince watchers that something unseeable actually exists. Once the watchers are convinced, the unseeable thing exists as if it were real.","40","Psychic",0,"122");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(123, "Scyther","Scyther is blindingly fast. Its blazing speed enhances the effectiveness of the twin scythes on its forearms. This Pokémon's scythes are so effective, they can slice through thick logs in one wicked stroke.","70","Bug",0,"123");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(124, "Jynx","Jynx walks rhythmically, swaying and shaking its hips as if it were dancing. Its motions are so bouncingly alluring, people seeing it are compelled to shake their hips without giving any thought to what they are doing.","70","Psychic",0,"124");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(125, "Electabuzz","When a storm arrives, gangs of this Pokémon compete with each other to scale heights that are likely to be stricken by lightning bolts. Some towns use Electabuzz in place of lightning rods.","70","Electric",0,"125");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(126, "Magmar","In battle, Magmar blows out intensely hot flames from all over its body to intimidate its opponent. This Pokémon's fiery bursts create heat waves that ignite grass and trees in its surroundings.","70","Fire",0,"126");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(127, "Pinsir","Pinsir is astoundingly strong. It can grip a foe weighing twice its weight in its horns and easily lift it. This Pokémon's movements turn sluggish in cold places.","60","Bug",0,"127");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(128, "Tauros","This Pokémon is not satisfied unless it is rampaging at all times. If there is no opponent for Tauros to battle, it will charge at thick trees and knock them down to calm itself.","60","Normal",0,"128");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(129, "Magikarp","Magikarp is a pathetic excuse for a Pokémon that is only capable of flopping and splashing. This behavior prompted scientists to undertake research into it.","30","Water",0,"129");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(130, "Gyarados","When Magikarp evolves into Gyarados, its brain cells undergo a structural transformation. It is said that this transformation is to blame for this Pokémon's wildly violent nature.","100","Dragon",0,"130");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(131, "Lapras","People have driven Lapras almost to the point of extinction. In the evenings, this Pokémon is said to sing plaintively as it seeks what few others of its kind still remain.","80","Ice",0,"131");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(132, "Ditto","Ditto rearranges its cell structure to transform itself into other shapes. However, if it tries to transform itself into something by relying on its memory, this Pokémon manages to get details wrong.","50","Normal",0,"132");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(133, "Eevee","Eevee has an unstable genetic makeup that suddenly mutates due to the environment in which it lives. Radiation from various stones causes this Pokémon to evolve.","50","Normal",0,"133");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(134, "Vaporeon","Vaporeon underwent a spontaneous mutation and grew fins and gills that allow it to live underwater. This Pokémon has the ability to freely control water.","80","Water",0,"134");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(135, "Jolteon","Jolteon's cells generate a low level of electricity. This power is amplified by the static electricity of its fur, enabling the Pokémon to drop thunderbolts. The bristling fur is made of electrically charged needles.","70","Electric",0,"135");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(136, "Flareon","Flareon's fluffy fur has a functional purpose—it releases heat into the air so that its body does not get excessively hot. This Pokémon's body temperature can rise to a maximum of 1,650 degrees Fahrenheit.","70","Fire",0,"136");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(137, "Porygon","Porygon is capable of reverting itself entirely back to program data and entering cyberspace. This Pokémon is copy protected so it cannot be duplicated by copying.","30","Normal",0,"137");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(138, "Omanyte","Omanyte is one of the ancient and long-since-extinct Pokémon that have been regenerated from fossils by people. If attacked by an enemy, it withdraws itself inside its hard shell.","40","Water",0,"138");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(139, "Omastar","Omastar uses its tentacles to capture its prey. It is believed to have become extinct because its shell grew too large and heavy, causing its movements to become too slow and ponderous.","70","Water",0,"139");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(140, "Kabuto","Kabuto is a Pokémon that has been regenerated from a fossil. However, in extremely rare cases, living examples have been discovered. The Pokémon has not changed at all for 300 million years.","30","Rock",0,"140");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(141, "Kabutops","Kabutops swam underwater to hunt for its prey in ancient times. The Pokémon was apparently evolving from being a water dweller to living on land as evident from the beginnings of change in its gills and legs.","60","Rock",0,"141");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(142, "Aerodactyl","Aerodactyl is a Pokémon from the age of dinosaurs. It was regenerated from genetic material extracted from amber. It is imagined to have been the king of the skies in ancient times.","60","Rock",0,"142");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(143, "Snorlax","Snorlax's typical day consists of nothing more than eating and sleeping. It is such a docile Pokémon that there are children who use its expansive belly as a place to play.","90","Psychic",0,"143");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(144, "Articuno","Articuno is a legendary bird Pokémon that can control ice. The flapping of its wings chills the air. As a result, it is said that when this Pokémon flies, snow will fall.","70","Ice",0,"144");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(145, "Zapdos","Zapdos is a legendary bird Pokémon that has the ability to control electricity. It usually lives in thunderclouds. The Pokémon gains power if it is stricken by lightning bolts.","80","Electric",0,"145");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(146, "Moltres","Moltres is a legendary bird Pokémon that has the ability to control fire. If this Pokémon is injured, it is said to dip its body in the molten magma of a volcano to burn and heal itself.","70","Fire",0,"146");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(147, "Dratini","Dratini continually molts and sloughs off its old skin. It does so because the life energy within its body steadily builds to reach uncontrollable levels.","40","Dragon",0,"147");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(148, "Dragonair","Dragonair stores an enormous amount of energy inside its body. It is said to alter weather conditions in its vicinity by discharging energy from the crystals on its neck and tail.","80","Dragon",0,"148");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(149, "Dragonite","Dragonite is capable of circling the globe in just 16 hours. It is a kindhearted Pokémon that leads lost and foundering ships in a storm to the safety of land.","80","Dragon",0,"149");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(150, "Mewtwo","Mewtwo is a Pokémon that was created by genetic manipulation. However, even though the scientific power of humans created this Pokémon's body, they failed to endow Mewtwo with a compassionate heart.","80","Psychic",0,"150");
        PokemonDatabaseHelper.getInstance(mHelperContext).insertPokemon(151, "Mew","Mew is said to possess the genetic composition of all Pokémon. It is capable of making itself invisible at will, so it entirely avoids notice even if it approaches people.","100","Psychic",0,"151");

    }

    //method to search pokemon
    public Cursor searchPokemon(String query){

        Log.d("searchplaces", "did we get here?");
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("searchplaces", "did we get here also?");

        Cursor cursor = db.query(POKEMON_TABLE_NAME, // a. table
                POKEMON_COLUMNS, // b. column names
                COL_POKEMON_NAME+" LIKE ?", // c. selections
                new String[]{"%"+query+"%"}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        return cursor;
    }

    public Cursor searchType(String query){

        Log.d("searchplaces", "did we get here?");
        SQLiteDatabase db = this.getReadableDatabase();
        Log.d("searchplaces", "did we get here also?");

        Cursor cursor = db.query(POKEMON_TABLE_NAME, // a. table
                POKEMON_COLUMNS, // b. column names
                COL_POKEMON_NAME + " LIKE ? OR "+COL_POKEMON_TYPE+"=?" ,// c. selections
                new String[]{"%"+query+"%",query}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        return cursor;
    }

    //these next few methods pull specific data out of the respective columns
    public String getPokemonName(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(POKEMON_TABLE_NAME, // a. table
                new String[] {COL_POKEMON_NAME}, // b. column names
                COL_ID + "= ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        if(cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COL_POKEMON_NAME));
        }else{
            return "Description Not Found";
        }
    }

    public String getPokemonDescription(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(POKEMON_TABLE_NAME, // a. table
                new String[] {COL_POKEMON_DESCRIPTION}, // b. column names
                COL_ID + "= ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        if(cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COL_POKEMON_DESCRIPTION));
        }else{
            return "Description Not Found";
        }
    }

    public String getPokemonHp(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(POKEMON_TABLE_NAME, // a. table
                new String[] {COL_POKEMON_HP}, // b. column names
                COL_ID + "= ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        if(cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COL_POKEMON_HP));
        }else{
            return "Description Not Found";
        }
    }

    public String getPokemonType(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(POKEMON_TABLE_NAME, // a. table
                new String[] {COL_POKEMON_TYPE}, // b. column names
                COL_ID + "= ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        if(cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COL_POKEMON_TYPE));
        }else{
            return "Description Not Found";
        }
    }

    public String getPokemonImg(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(POKEMON_TABLE_NAME, // a. table
                new String[] {COL_IMG}, // b. column names
                COL_ID + "= ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        if(cursor.moveToFirst()) {
            return cursor.getString(cursor.getColumnIndex(COL_IMG));
        }else{
            return "Description Not Found";
        }
    }

    //this method makes all pokemon with a 1 in their COL_IS_FAVORITE a favorite (all pokemon start with a 0 in this column)
    public Cursor getFavoritesList(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(POKEMON_TABLE_NAME, // a. table
                POKEMON_COLUMNS, // b. column names
                COL_IS_FAVORITE+ "= ?", // c. selections
                new String[]{"1"}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        return cursor;
    }

    //method that updates the database, where value is 0 if not, or 1 if favrotied
    public void update (String pokemonID,int value){

        ContentValues values = new ContentValues();
        values.put("FAVORITE" , value);

        SQLiteDatabase db = this.getWritableDatabase();
        db.update(POKEMON_TABLE_NAME,//table name
                values,//content values
                COL_ID+"=?",// COL_ID+"=?"
                new String[] {pokemonID});
    }


    public int getPokemonFavorited(int id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(POKEMON_TABLE_NAME, // a. table
                new String[]{COL_IS_FAVORITE}, // b. column names
                COL_ID + "= ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        if(cursor.moveToFirst()) {
            return cursor.getInt(cursor.getColumnIndex(COL_IS_FAVORITE));
        }else{
            return 0;
        }
    }



}
