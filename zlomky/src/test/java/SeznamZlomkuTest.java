

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.SeznamZlomku;
import com.example.Zlomek;

import static org.junit.jupiter.api.Assertions.*;

class SeznamZlomkuTest {
    SeznamZlomku seznam;

    @BeforeEach
    void setUp() {
        seznam = new SeznamZlomku();
    }

    @Test
    @DisplayName("Test pridavani")
    void pridatZlomek() {
        seznam.pridat(new Zlomek(3, 5));
        assertEquals(1, seznam.pocet(), "Pocet zlomku");
        Zlomek z = seznam.vybrat(0);
        assertEquals(3, z.getCitatel(), "Citatel");
        assertEquals(5, z.getJmenovatel(), "Jmenovatel");
    }

    @Test
    @DisplayName("Test pridani null")
    void pridatZlomekNull() {
        assertThrows(NullPointerException.class, () -> seznam.pridat(null));
    }

    @Test
    void vybratZlomek() {
        seznam.pridat(new Zlomek(3, 5));
        Zlomek z = seznam.vybrat(0);
        assertEquals(3, z.getCitatel(), "Citatel");
        assertEquals(5, z.getJmenovatel(), "Jmenovatel");
    }

    @Test
    void vybratZlomekZPrazdneho() {
        assertThrows(IndexOutOfBoundsException.class, () -> seznam.vybrat(0));
    }

    @Test
    void odebratZlomek() {
        seznam.pridat(new Zlomek(3,5));
        seznam.odebrat(0);
        assertEquals(0, seznam.pocet(),"Ma byt prazdny");
    }

    @Test
    void vypisZlomky() {
        // tu neni co
    }

    @Test
    void spoctiSoucet() {
        seznam.pridat(new Zlomek(1,2));
        seznam.pridat(new Zlomek(1,3));
        seznam.pridat(new Zlomek(1,4));
        Zlomek soucet = seznam.soucet();
        assertEquals(13, soucet.getCitatel(),  "Citatel");
        assertEquals(12, soucet.getJmenovatel(), "Jmenovatel");
    }

    @Test
    void spoctiSoucetZPrazdneho() {
        Zlomek soucet = seznam.soucet();
        assertEquals(0, soucet.getCitatel(),  "Citatel");
        assertEquals(1, soucet.getJmenovatel(), "Jmenovatel");
    }

    @Test
    void spoctiPrumer() {
        seznam.pridat(new Zlomek(1,2));
        seznam.pridat(new Zlomek(1,4));
        Zlomek prumer = seznam.prumer();
        assertEquals(3, prumer.getCitatel(),  "Citatel");
        assertEquals(8, prumer.getJmenovatel(), "Jmenovatel");
    }

    @Test
    void spoctiPrumerZPrazdneho() {
        Zlomek prumer = seznam.prumer();
        assertEquals(0, prumer.getCitatel(),  "Citatel");
        assertEquals(1, prumer.getJmenovatel(), "Jmenovatel");
    }
}