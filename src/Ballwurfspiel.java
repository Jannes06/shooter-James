import GLOOP.*;
import java.awt.*;
public  class Ballwurfspiel{
    GLKamera   kamera;
    GLLicht    licht;
    GLHimmel   himmel;
    GLTastatur tastatur;
    GLMaus     maus;
    GLZylinder zylinder0,zylinder1, zylinder2,zylinder3,zylinder4,kugel,lauf,korper,kreuzwa,kreuzse;
    GLWuerfel  vorne,hintenoben,hintenunten,ganzhinten,schulter,magazin,punktForKameraScope,punktForKameraNormal,punktForKameraNormalBlickrichtung,punktForKugel,wandlinks,wandrechts,wandhinten;
    GLWuerfel  wandHintenUnten,wandvorne,decke,wandVorneUnten,balkenLinks,balkenRechts,balkenWaagerecht,wandVorneOben;
    GLTorus    scope1,abzug;
    GLBoden    boden;
    GLTafel    tafel1, tafel2,tafel3;
    Robot      robomaus;

    public void Ballwurfspiel()throws AWTException{
        //Kamera, Licht, Himmel und Tastatur
        //kamera   = new GLEntwicklerkamera();
        //Kamera
        kamera = new GLKamera ();
        licht    = new GLLicht(0,800,0); 
        himmel   = new GLHimmel("src/Himmel.jpg");
        tastatur = new GLTastatur();
        maus = new GLMaus();
        boden = new GLBoden("src/Metalltextur.jfif");

        robomaus = new Robot();
        //Tafel

        tafel2 = new GLTafel(0,250,-299, 150,50);
        tafel2.setzeFarbe(69,69,69);
        

        tafel3 = new GLTafel(0,200,-300, 200,50);
        tafel3. setzeFarbe(69,69,69);
        tafel3. setzeText("Punkte = 0",50);
        //Anfangsmunition
        int x=10;
        //Koordinate zum verschieben von x des Zieles
        double zielx= -100;

        //Koordinate f�r die Scope Kamera
        double scopex;
        double scopey;
        double scopez;

        //Koordinate von Kamerapunkt hinter dem scope
        double punktx;
        double punkty;   
        double punktz;

        //Koordinate von Kamerapunkt neben der Waffe
        double punkt1x;
        double punkt1y;   
        double punkt1z;

        //Koordinate vom Blickpunkt der Kamera neben der Waffe

        double punkt2x;
        double punkt2y;   
        double punkt2z;

        //Koordinaten zum Hinfliegen der Kugel (Bickpunkt)
        double kugelpunktx;
        double kugelpunkty;
        double kugelpunktz;

        
        double laufx;
        double laufy;
        double laufz;

        double winkelx;
        double winkely;
        
        double winkelyStandart;
        double winkelxStandart;
        //Sprint
        double speedw;
        //Map bauen
   
        //W�nde
        wandlinks = new GLWuerfel (-1000,250,-1800,10);
        wandlinks.setzeSkalierung(1,200,1500);
        wandlinks.setzeTextur("src/WandGlatt.jfif");
        wandlinks.drehe(0,180,0);

        
        wandrechts = new GLWuerfel (1000,250,-1800,10);
        wandrechts.setzeSkalierung(1,200,1500);
        wandrechts.setzeTextur("src/WandGlatt.jfif");
 
        wandvorne = new GLWuerfel (0,250,-5000,10);
        wandvorne.setzeSkalierung(1,200,500);
        wandvorne.drehe(0,90,0);
        wandvorne.setzeTextur("src/Wandmitloch.jfif");
        
        wandhinten = new GLWuerfel (0,250,1000,10);
        wandhinten.setzeSkalierung(1,200,500);
        wandhinten.drehe(0,90,0);
        wandhinten.setzeTextur("src/Wandglatt.jfif");
        
        
        wandVorneUnten = new GLWuerfel (0,-200,-5300,10);
        wandVorneUnten.setzeSkalierung(100,100,500);
        wandVorneUnten.drehe(0,90,0);
        wandVorneUnten.setzeTextur("src/HintenObenUnten.jfif");
        
        wandVorneOben = new GLWuerfel (0,1100,-5300,10);
        wandVorneOben.setzeSkalierung(100,50,500);
        wandVorneOben.drehe(0,90,0);
        wandVorneOben.setzeTextur("src/HintenObenUnten.jfif");
        
        wandHintenUnten = new GLWuerfel (0,-200,-500,10);
        wandHintenUnten.setzeSkalierung(30,100,500);
        wandHintenUnten.drehe(0,90,0);
        wandHintenUnten.setzeTextur("src/Metall1.jfif");
        
        
        decke = new GLWuerfel (0,1200,-2000,10);
        decke.setzeSkalierung(200,0,700);
        decke.setzeTextur("src/Metalltextur.jfif");
        
         
        balkenLinks = new GLWuerfel (-300,792,-2000,10);
        balkenLinks.setzeSkalierung(3,3,600);
        balkenLinks.setzeTextur("src/MagazinTextur.jfif");
        
        balkenRechts = new GLWuerfel (300,792,-2000,10);
        balkenRechts.setzeSkalierung(3,3,600);
        balkenRechts.setzeTextur("src/MagazinTextur.jfif");
        
        balkenWaagerecht = new GLWuerfel (0,762,-3400,10);
        balkenWaagerecht.setzeSkalierung(70,3,3);
        balkenWaagerecht.setzeTextur("src/MagazinTextur.jfif");
        
        /*tafel1 = new GLTafel(0,0,0, 10,5);
        tafel1.drehe(0,-90,0);
        tafel1.setzeFarbe(1,1,1);
        tafel1.setzeText("10", 5); */

        //Zielscheibe
        //innenring schwarz
        /*zylinder0 = new GLZylinder (zielx,500,-3397, 10,30); 
        zylinder0.setzeFarbe( 0,0,0);

        //Innenring Rot
        zylinder1 = new GLZylinder (zielx,500,-3398, 30,30); 
        zylinder1.setzeFarbe( 1,0,0); 

        //MitteInnenRing Gr�n
        zylinder2 = new GLZylinder (zielx,500,-3399, 80,30); 
        zylinder2.setzeFarbe( 0,1,0);  

        //MitteAu�enRing Blau
        zylinder3 = new GLZylinder (zielx,500,-3400, 150,30); 
        zylinder3.setzeFarbe( 0,0,1);   
        */
        //Au�enRing Wei�
        zylinder4 = new GLZylinder (zielx,600,-3401, 160,30); 
        zylinder4.setzeFarbe( 1,1,1);    
        zylinder4.setzeTextur("src/Zielscheibe.jfif");
        
        
         
        int punkte=0;
        for (int i=0; i<10;i++) {     

            //Pfeil erzeugen
            double kx = Math.random() * 200 ;   
            double ky = Math.random()  +500;  
            double kz = 500;
            
            kugel = new GLZylinder(kx,ky,kz, 3,50);
            kugel.setzeTextur ("src/Gold.jfif");

            //Zielfernrohr
            scope1 = new GLTorus (kx,ky+10,kz+50, 6,1);
            scope1.setzeSkalierung(1,1,17);
            scope1.setzeTextur("src/ScopeTextur.jfif");
            scope1.drehe(-1,0,0);
            // Fadenkreuz

            //Senkrecht
            kreuzse = new GLZylinder (kx,ky+7,kz+50, 0.08,20);
            kreuzse.drehe(90,0,0);
            kreuzse.setzeFarbe (0,0,0);

            //Waagerecht
            kreuzwa = new GLZylinder (kx,ky+9.6,kz+50, 0.08,12);
            kreuzwa.drehe(0,90,0);
            kreuzwa.setzeFarbe (0,0,0);

            // WaffenLauf   
            lauf = new GLZylinder (kx,ky,kz-30, 4,150);
            lauf.setzeFarbe (0,0,0);
            //Waffenk�rper
            korper = new GLZylinder (kx,ky-3,kz+40, 7,100);
            korper.setzeTextur("src/wood _texture3177.jpg");
            //Schulterst�tze

            //Vorne
            vorne = new GLWuerfel (kx,ky-10,kz+85,18);
            vorne.setzeSkalierung(0.75,1,1.3);
            vorne.drehe(30,0,0);
            vorne.setzeTextur("src/wood _texture3177.jpg");

            //BalkenOben
            hintenoben = new GLWuerfel (kx,ky-2,kz+90,15);
            hintenoben.setzeSkalierung(0.5,0.5,3);
            hintenoben.setzeTextur("src/wood _texture3177.jpg");

            //BalkenUnten
            hintenunten = new GLWuerfel (kx,ky-10,kz+100,15);
            hintenunten.setzeSkalierung(0.5,1,1.8);
            hintenunten.drehe(-28,0,0);
            hintenunten.setzeTextur("src/wood _texture3177.jpg");

            //Balkenhinten
            ganzhinten = new GLWuerfel (kx,ky-5,kz+123,15);
            ganzhinten.setzeSkalierung(0.5,0.7,1.8);
            ganzhinten.setzeTextur("src/wood _texture3177.jpg");
            //Schulterst�tze

            schulter = new GLWuerfel (kx,ky-10,kz+140,15);
            schulter.setzeSkalierung(0.75,1.7,1.8);
            schulter.setzeFarbe(0,0,0);
            //abzug
            abzug = new GLTorus (kx,ky-15,kz+70, 6,2);
            abzug.drehe(0,90,0);
            abzug.setzeFarbe(0,0,0);

            //Magazin
            magazin = new GLWuerfel (kx,ky-10,kz+40,15);
            magazin.setzeSkalierung(0.5,1.3,1);
            magazin.setzeTextur("src/MagazinTextur.jfif");

            //Munitionsanzeige
            tafel1 = new GLTafel(0,0,0, 10,5);
            tafel1.setzePosition(kx-4,ky-15,kz+40);
            tafel1.drehe(0,-90,0);
            tafel1.setzeFarbe(1,1,1);
            tafel1.setzeText(""+x+"",5);
            
            
            
            //Kamera
            //kamera = new GLKamera ();
            kamera.setzePosition(kx-35,ky+5,kz+120);
            kamera.setzeBlickpunkt(ky,kx-100,-500);

            //Punkt f�r die  koordinate vor dem scope der kamera
            punktForKameraScope = new GLWuerfel (kx,ky+10,kz+100,0.000001);
            punktForKameraScope.setzeFarbe(0,1,0);

            //Punkt f�r die Kamera neben der Waffe in der Normalansicht
            punktForKameraNormal = new GLWuerfel (kx-25,ky+6,kz+85,0.000000001);
            punktForKameraNormal.setzeFarbe(0,1,0);

            //Punkt f�r die Blickrichtung der kamera in der Normalansicht

            punktForKameraNormalBlickrichtung = new GLWuerfel (kx-20,ky+5,kz,0.0000001);
            punktForKameraNormalBlickrichtung.setzeFarbe(0,1,0);

            //punktf�rKugel
            punktForKugel = new GLWuerfel (kx,ky,kz-10000,0.0000001);
            punktForKugel.setzeFarbe(0,1,0);

            
            Sys.warte(500);

            boolean var=true; 
            double speedZiel=7;
            //while (zylinder0.gibX()<200) {    
              
            while (!maus.gedruecktLinks()){
                Sys.warte(1);     
                //Ziel Geschwindigkeit
                if(tastatur.links()) {
                 
                 speedZiel=speedZiel-1;
                 Sys.warte(50);
                
                }
                if (speedZiel<1){
                 speedZiel=speedZiel+1;
                 
                }
                if(tastatur.rechts()) {
                 speedZiel=speedZiel+1;
                 Sys.warte(50);
                }                
                if (speedZiel>100){
                 speedZiel=speedZiel-1;
                }
                tafel2.setzeText("Geschwindigkeit "+speedZiel+"",50);
                
                //while  (!tastatur.enter()) { 
                if ((zylinder4.gibX()<300 ) && ( var==true))  {         
                    /*zylinder0.verschiebe(0.7,0,0);
                    zylinder1.verschiebe(0.7,0,0);
                    zylinder2.verschiebe(0.7,0,0);
                    zylinder3.verschiebe(0.7,0,0);*/
                    zylinder4.verschiebe(speedZiel/10,0,0);
                    Sys.warte(7);
                }
                else{ 
                    var=false;
                }
                if ((zylinder4.gibX()>-300) && (var==false)) {       

                    /*zylinder0.verschiebe(-0.7,0,0);
                    zylinder1.verschiebe(-0.7,0,0);
                    zylinder2.verschiebe(-0.7,0,0);
                    zylinder3.verschiebe(-0.7,0,0);*/
                    zylinder4.verschiebe(-speedZiel/10,0,0);    
                    Sys.warte(7);
                }
                else{
                    var=true;
                }  
                speedw=-3;
                if (tastatur.shift()) { 
                    speedw=-10;

                }
                if (tastatur.istGedrueckt('a')) {      
                    kugel.verschiebe(-3,0,0);
                    scope1.verschiebe(-3,0,0);
                    lauf.verschiebe(-3,0,0);
                    korper.verschiebe(-3,0,0);
                    vorne.verschiebe(-3,0,0);
                    hintenoben.verschiebe(-3,0,0);
                    hintenunten.verschiebe(-3,0,0);
                    ganzhinten.verschiebe(-3,0,0);
                    schulter.verschiebe(-3,0,0);
                    abzug.verschiebe(-3,0,0);
                    kamera.verschiebe(-3,0,0);
                    magazin.verschiebe(-3,0,0);
                    tafel1.verschiebe(-3,0,0);
                    kreuzse.verschiebe(-3,0,0);
                    kreuzwa.verschiebe(-3,0,0);
                    punktForKameraScope.verschiebe(-3,0,0);
                    punktForKameraNormal.verschiebe(-3,0,0);
                    punktForKameraNormalBlickrichtung.verschiebe(-3,0,0);
                    punktForKugel.verschiebe(-3,0,0);
                    kx=kx-3;
                }
                if (tastatur.istGedrueckt('d')) {      
                    kugel.verschiebe(3,0,0);
                    scope1.verschiebe(3,0,0);
                    lauf.verschiebe(3,0,0);
                    korper.verschiebe(3,0,0);
                    vorne.verschiebe(3,0,0);
                    hintenoben.verschiebe(3,0,0);
                    hintenunten.verschiebe(3,0,0);
                    ganzhinten.verschiebe(3,0,0);
                    schulter.verschiebe(3,0,0);
                    abzug.verschiebe(3,0,0);
                    kamera.verschiebe(3,0,0);
                    magazin.verschiebe(3,0,0);
                    tafel1.verschiebe(3,0,0);
                    kreuzse.verschiebe(3,0,0);
                    kreuzwa.verschiebe(3,0,0);
                    punktForKameraScope.verschiebe(3,0,0);
                    punktForKameraNormal.verschiebe(3,0,0);
                    punktForKameraNormalBlickrichtung.verschiebe(3,0,0);
                    punktForKugel.verschiebe(3,0,0);
                    kx=kx+3;
                }
                if (tastatur.istGedrueckt('w')) {      
                    kugel.verschiebe(0,0,speedw);
                    scope1.verschiebe(0,0,speedw);
                    lauf.verschiebe(0,0,speedw);
                    korper.verschiebe(0,0,speedw);
                    vorne.verschiebe(0,0,speedw);
                    hintenoben.verschiebe(0,0,speedw);
                    hintenunten.verschiebe(0,0,speedw);
                    ganzhinten.verschiebe(0,0,speedw);
                    schulter.verschiebe(0,0,speedw);
                    abzug.verschiebe(0,0,speedw);
                    kamera.verschiebe(0,0,speedw);
                    magazin.verschiebe(0,0,speedw);
                    tafel1.verschiebe(0,0,speedw);
                    kreuzse.verschiebe(0,0,speedw);
                    kreuzwa.verschiebe(0,0,speedw);
                    punktForKameraScope.verschiebe(0,0,speedw);
                    punktForKameraNormal.verschiebe(0,0,speedw);
                    punktForKameraNormalBlickrichtung.verschiebe(0,0,speedw);
                    punktForKugel.verschiebe(0,0,speedw);
                    kz=kz+speedw;
                }
                if (tastatur.istGedrueckt('s')) {      
                    kugel.verschiebe(0,0,4);
                    scope1.verschiebe(0,0,4);
                    lauf.verschiebe(0,0,4);
                    korper.verschiebe(0,0,4);
                    vorne.verschiebe(0,0,4);
                    hintenoben.verschiebe(0,0,4);
                    hintenunten.verschiebe(0,0,4);
                    ganzhinten.verschiebe(0,0,4);
                    schulter.verschiebe(0,0,4);
                    abzug.verschiebe(0,0,4);
                    kamera.verschiebe(0,0,4);
                    magazin.verschiebe(0,0,4);
                    tafel1.verschiebe(0,0,4);
                    kreuzse.verschiebe(0,0,4);
                    kreuzwa.verschiebe(0,0,4);
                    punktForKameraScope.verschiebe(0,0,4);
                    punktForKameraNormal.verschiebe(0,0,4);
                    punktForKameraNormalBlickrichtung.verschiebe(0,0,4);
                    punktForKugel.verschiebe(0,0,4);
                    kz=kz+4;
                }
                //Distanzeinstellungen 
                if(tastatur.oben()) {
                 zylinder4.verschiebe(0,0,-100);
                 balkenWaagerecht.verschiebe(0,0,-100);
                 Sys.warte(50);
                
                }
                if (zylinder4.gibZ()<-5000){
                 zylinder4.verschiebe(0,0,100);
                 balkenWaagerecht.verschiebe(0,0,100);
                }
                if(tastatur.unten()) {
                 zylinder4.verschiebe(0,0,100);
                 balkenWaagerecht.verschiebe(0,0,100);
                 Sys.warte(50);
                }                
                if (zylinder4.gibZ()>-1000){
                 zylinder4.verschiebe(0,0,-100);
                 balkenWaagerecht.verschiebe(0,0,-100);
                }
                
                
                //Oben und Unten
                if ((maus.gibY()!=0) &&  (  (maus.gibX() !=0 ))) {
                    winkely = maus.gibY()-509;
                    winkely = winkely*-1;
                    winkely = winkely/10;

                    kugel.drehe(winkely,0,0, kx,ky,kz);
                    scope1.drehe(winkely,0,0, kx,ky,kz);
                    lauf.drehe(winkely,0,0, kx,ky,kz);
                    korper.drehe(winkely,0,0, kx,ky,kz);
                    vorne.drehe(winkely,0,0, kx,ky,kz);
                    hintenoben.drehe(winkely,0,0, kx,ky,kz);
                    hintenunten.drehe(winkely,0,0, kx,ky,kz);
                    ganzhinten.drehe(winkely,0,0, kx,ky,kz);
                    schulter.drehe(winkely,0,0, kx,ky,kz);
                    abzug.drehe(winkely,0,0, kx,ky,kz);
                    magazin.drehe(winkely,0,0, kx,ky,kz);
                    tafel1.drehe(winkely,0,0, kx,ky,kz);
                    kreuzse.drehe(winkely,0,0, kx,ky,kz);
                    kreuzwa.drehe(winkely,0,0, kx,ky,kz);            
                    kamera.rotiere( winkely,1,0,0,kx,ky,kz);
                    punktForKameraScope.drehe(winkely,0,0, kx,ky,kz);
                    punktForKameraNormal.drehe(winkely,0,0, kx,ky,kz);
                    punktForKameraNormalBlickrichtung.drehe(winkely,0,0, kx,ky,kz);
                    punktForKugel.drehe(winkely,0,0, kx,ky,kz);
                    

 
                }
                //Links und Rechts
                if ((maus.gibX()!=0) &&  ((maus.gibY() !=0 ))) {
                    winkelx = maus.gibX()-955;
                    winkelx= winkelx*-1;
                    winkelx = winkelx/10;

                    
                    kugel.drehe(0,winkelx,0, kx,ky,kz);
                    scope1.drehe(0,winkelx,0, kx,ky,kz);
                    lauf.drehe(0,winkelx,0, kx,ky,kz);
                    korper.drehe(0,winkelx,0, kx,ky,kz);
                    vorne.drehe(0,winkelx,0, kx,ky,kz);
                    hintenoben.drehe(0,winkelx,0, kx,ky,kz);
                    hintenunten.drehe(0,winkelx,0, kx,ky,kz);
                    ganzhinten.drehe(0,winkelx,0, kx,ky,kz);
                    schulter.drehe(0,winkelx,0, kx,ky,kz);
                    abzug.drehe(0,winkelx,0, kx,ky,kz);
                    magazin.drehe(0,winkelx,0, kx,ky,kz);
                    tafel1.drehe(0,winkelx,0, kx,ky,kz);
                    kreuzse.drehe(0,winkelx,0, kx,ky,kz);
                    kreuzwa.drehe(0,winkelx,0, kx,ky,kz);            
                    kamera.rotiere( winkelx,0,1,0,kx,ky,kz);
                    punktForKameraScope.drehe(0,winkelx,0, kx,ky,kz);
                    punktForKameraNormal.drehe(0,winkelx,0, kx,ky,kz);
                    punktForKameraNormalBlickrichtung.drehe(0,winkelx,0, kx,ky,kz);
                    punktForKugel.drehe(0,winkelx,0, kx,ky,kz);
                    
                    
                    //winkelxStandart = winkelx + winkelx;
                }
                robomaus.mouseMove(960, 540);

                //Damit man nicht unter sich gucken kann
                if (lauf.gibY()<477) {
                    kugel.drehe(5,0,0, kx,ky,kz);
                    scope1.drehe(5,0,0, kx,ky,kz);
                    lauf.drehe(5,0,0, kx,ky,kz);
                    korper.drehe(5,0,0, kx,ky,kz);
                    vorne.drehe(5,0,0, kx,ky,kz);
                    hintenoben.drehe(5,0,0, kx,ky,kz);
                    hintenunten.drehe(5,0,0, kx,ky,kz);
                    ganzhinten.drehe(5,0,0, kx,ky,kz);
                    schulter.drehe(5,0,0, kx,ky,kz);
                    abzug.drehe(5,0,0, kx,ky,kz);
                    magazin.drehe(5,0,0, kx,ky,kz);
                    tafel1.drehe(5,0,0, kx,ky,kz);
                    kreuzse.drehe(5,0,0, kx,ky,kz);
                    kreuzwa.drehe(5,0,0, kx,ky,kz);            
                    kamera.rotiere( 5,1,0,0,kx,ky,kz);
                    punktForKameraScope.drehe(5,0,0, kx,ky,kz);
                    punktForKameraNormal.drehe(5,0,0, kx,ky,kz);
                    punktForKameraNormalBlickrichtung.drehe(5,0,0, kx,ky,kz);
                    punktForKugel.drehe(5,0,0, kx,ky,kz);

                
                
                }

                
                
                
                
                //Zielen
                if (maus.gedruecktRechts()){
                    scopex = scope1.gibX();
                    scopey = scope1.gibY();
                    scopez = scope1.gibZ();

                    punktx = punktForKameraScope.gibX();
                    punkty = punktForKameraScope.gibY();
                    punktz = punktForKameraScope.gibZ();

                    kamera.setzePosition(punktx,punkty,punktz);
                    kamera.setzeBlickpunkt(scopex,scopey,scopez);

                }
                else {

                    punkt1x = punktForKameraNormal.gibX();
                    punkt1y = punktForKameraNormal.gibY();
                    punkt1z = punktForKameraNormal.gibZ();

                    punkt2x = punktForKameraNormalBlickrichtung.gibX();
                    punkt2y = punktForKameraNormalBlickrichtung.gibY();
                    punkt2z = punktForKameraNormalBlickrichtung.gibZ();

                    kamera.setzePosition(punkt1x,punkt1y,punkt1z);
                    kamera.setzeBlickpunkt(punkt2x,punkt2y,punkt2z);
                }
            }

            //Die Koordinaten zum Zielpunkt der Kugel

            kugelpunktx = punktForKugel.gibX()-kugel.gibX();
            kugelpunkty = punktForKugel.gibY()-kugel.gibY();
            kugelpunktz = punktForKugel.gibZ()-kugel.gibZ();

            kugelpunktx = kugelpunktx/1000;
            kugelpunkty = kugelpunkty/1000;
            kugelpunktz = kugelpunktz/1000;

            int schussbahn=0;   
            while( schussbahn==0){
                //Satz des Pytghagoras
                //a
                float xkugel = kugel.gibX();
                float xschwarz= zylinder4.gibX();

                float a=xkugel-xschwarz;
                //b
                float ykugel=kugel.gibY();
                float yschwarz= zylinder4.gibY();

                float b=ykugel-yschwarz;

                // c berechnen

                double c= Math.sqrt(a*a+b*b);
                
                if ( (kugel.gibZ() < zylinder4.gibZ()+15) && (kugel.gibZ() >zylinder4.gibZ()+5 ) && (c<160)       )  {
                    
                    schussbahn=1;
                    
                    //Wo man getroffen hat
                    kamera.setzePosition(zylinder4.gibX()-200,zylinder4.gibY()+50,zylinder4.gibZ()+400);
                    kamera.setzeBlickpunkt(zylinder4.gibX(),zylinder4.gibY(),zylinder4.gibZ()-500);
                }
                if ((kugel.gibZ()>punktForKugel.gibZ()) && (kugel.gibX()!=punktForKugel.gibX()  ) &&(kugel.gibY()!=punktForKugel.gibY())   )  {
                
                    kugel.verschiebe(kugelpunktx,kugelpunkty,kugelpunktz);
                    Sys.warte(1);

                }else{
                    schussbahn=2;
                }

                

            }
             Sys.warte(1000);
            
            
            
            
            
            //kamera.rotiere(winkelxStandart,winkelyStandart,0,0,kx,ky,kz);
            
            
            //Satz des Pytghagoras
                //a
                float xkugel = kugel.gibX();
                float xschwarz= zylinder4.gibX();

                float a=xkugel-xschwarz;
                //b
                float ykugel=kugel.gibY();
                float yschwarz= zylinder4.gibY();

                float b=ykugel-yschwarz;

                // c berechnen

                double c= Math.sqrt(a*a+b*b);
                tafel2.setzeText("Entfernung zur Mitte: "+c+" cm",50);
               
                
                x=x-1; 

                //ins schwarze
                if (c<23){
                    punkte=punkte+10;
                }

                //ins rote
                if ((c<45) && (c>22)){
                    punkte= punkte+9;  
                }

                //
                if ((c<67) && (c>44)){
                    punkte= punkte+8;  
                } 
                if ((c<89) && (c>66)){
                    punkte= punkte+7;  
                } 
                if ((c<111) && (c>88)){
                    punkte= punkte+6;  
                } 
                if ((c<134) && (c>110)){
                    punkte= punkte+5;  
                } 
                

                
                
                
                
                
                
                
                
                tafel3. setzeText("Punkte = "+punkte+"" ,50);

                Sys.warte(1500);
                kugel.loesche();
                scope1.loesche();
                lauf.loesche();
                korper.loesche();
                vorne.loesche();
                hintenoben.loesche();
                hintenunten.loesche();
                ganzhinten.loesche();
                schulter.loesche();
                abzug.loesche();
                magazin.loesche();
                kreuzse.loesche();
                kreuzwa.loesche();
                punktForKameraScope.loesche();
                punktForKameraNormal.loesche();
                punktForKameraNormalBlickrichtung.loesche();
                punktForKugel.loesche();
                tafel1.loesche();
                
            }
        }

    }

