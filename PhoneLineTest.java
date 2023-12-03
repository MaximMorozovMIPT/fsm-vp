import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

class PhoneLineTest {
    PhoneLine p;

    @BeforeEach
    void setUp() throws Exception {
        p = new PhoneLine();
    }

    @AfterEach
    void tearDown() throws Exception {
        p = null;
    }

    @Test
    public void TestCase1() {
        // onHook/nul onHook/nul

        try {
            p.onHook();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Idle, Transition: onHook");
        }

        try {
            p.onHook();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Idle, Transition: onHook");
        }
    }

    @Test
    public void TestCase2() {
        // offHook/soundDialTone onHook/disconnectedLine onHook/nul
        
        assertEquals(p.offHook(), "soundDialTone");
        assertEquals(p.onHook(), "disconnectedLine");

        try {
            p.onHook();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Idle, Transition: onHook");
        }
    }

    @Test
    public void TestCase3() {
        // offHook/soundDialTone offHook/nul onHook/disconnectedLine
        
        assertEquals(p.offHook(), "soundDialTone");

        try {
            p.offHook();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Ready, Transition: offHook");
        }

        assertEquals(p.onHook(), "disconnectedLine");
    }

    @Test
    public void TestCase4() {
        // offHook/soundDialTone offHook/nul validNumber/findConnection
        
        assertEquals(p.offHook(), "soundDialTone");

        try {
            p.offHook();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Ready, Transition: offHook");
        }

        assertEquals(p.validNumber(), "findConnection");
    }

    @Test
    public void TestCase5() {
        // offHook/soundDialTone offHook/nul I/I validNumber/findConnection
        
        assertEquals(p.offHook(), "soundDialTone");

        try {
            p.offHook();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Ready, Transition: offHook");
        }

        try {Thread.sleep(101); } catch (Exception ex) {}

        assertEquals(p.validNumber(), "findConnection");
    }

    @Test
    public void TestCase6() {
        // offHook/soundDialTone offHook/nul I/I I/I validNumber/findConnection
        
        assertEquals(p.offHook(), "soundDialTone");

        try {
            p.offHook();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Ready, Transition: offHook");
        }

        try {Thread.sleep(101); } catch (Exception ex) {}
        try {Thread.sleep(101); } catch (Exception ex) {}

        assertEquals(p.validNumber(), "findConnection");
    }

    @Test
    public void TestCase7() {
        // offHook/soundDialTone validNumber/findConnection onHook/disconnectedLine onHook/nul
        
        assertEquals(p.offHook(), "soundDialTone");
        assertEquals(p.validNumber(), "findConnection");
        assertEquals(p.onHook(), "disconnectedLine");

        try {
            p.onHook();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Idle, Transition: onHook");
        }
    }

    @Test
    public void TestCase8() {
        // offHook/soundDialTone validNumber/findConnection offHook/nul onHook/disconnectedLine
        
        assertEquals(p.offHook(), "soundDialTone");
        assertEquals(p.validNumber(), "findConnection");
        
        try {
            p.offHook();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Conversation, Transition: offHook");
        }

        assertEquals(p.onHook(), "disconnectedLine");
    }

    @Test
    public void TestCase9() {
        // offHook/soundDialTone validNumber/findConnection offHook/nul validNumber/continues
        
        assertEquals(p.offHook(), "soundDialTone");
        assertEquals(p.validNumber(), "findConnection");
        
        try {
            p.offHook();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Conversation, Transition: offHook");
        }

        assertEquals(p.validNumber(), "continues");
    }

    @Test
    public void TestCase10() {
        // offHook/soundDialTone validNumber/findConnection validNumber/continues onHook/disconnectedLine
        
        assertEquals(p.offHook(), "soundDialTone");
        assertEquals(p.validNumber(), "findConnection");
        assertEquals(p.validNumber(), "continues");
        assertEquals(p.onHook(), "disconnectedLine");        
    }

    @Test
    public void TestCase11() {
        // offHook/soundDialTone validNumber/findConnection validNumber/continues validNumber/continues
        
        assertEquals(p.offHook(), "soundDialTone");
        assertEquals(p.validNumber(), "findConnection");
        assertEquals(p.validNumber(), "continues");
        assertEquals(p.validNumber(), "continues");        
    }

    @Test
    public void TestCase12() {
        // offHook/soundDialTone validNumber/findConnection invalidNumber/continues onHook/disconnectedLine
        
        assertEquals(p.offHook(), "soundDialTone");
        assertEquals(p.validNumber(), "findConnection");
        assertEquals(p.invalidNumber(), "continues");
        assertEquals(p.onHook(), "disconnectedLine");        
    }

    @Test
    public void TestCase13() {
        // offHook/soundDialTone validNumber/findConnection invalidNumber/continues validNumber/continues
        
        assertEquals(p.offHook(), "soundDialTone");
        assertEquals(p.validNumber(), "findConnection");
        assertEquals(p.invalidNumber(), "continues");
        assertEquals(p.validNumber(), "continues");        
    }

    @Test
    public void TestCase14() {
        // offHook/soundDialTone validNumber/findConnection I/I onHook/disconnectedLine
        
        assertEquals(p.offHook(), "soundDialTone");
        assertEquals(p.validNumber(), "findConnection");
        try {Thread.sleep(101); } catch (Exception ex) {}
        assertEquals(p.onHook(), "disconnectedLine");
    }

    @Test
    public void TestCase15() {
        // offHook/soundDialTone validNumber/findConnection I/I validNumber/continues
        
        assertEquals(p.offHook(), "soundDialTone");
        assertEquals(p.validNumber(), "findConnection");
        try {Thread.sleep(101); } catch (Exception ex) {}
        assertEquals(p.validNumber(), "continues");
    }

    @Test
    public void TestCase16() {
        // offHook/soundDialTone invalidNumber/playMessage onHook/disconnectedLine onHook/nul
        
        assertEquals(p.offHook(), "soundDialTone");
        assertEquals(p.invalidNumber(), "PlayMessage");
        assertEquals(p.onHook(), "disconnectedLine");

        try {
            p.onHook();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Idle, Transition: onHook");
        }
    }

    @Test
    public void TestCase17() {
        // offHook/soundDialTone invalidNumber/playMessage offHook/nul onHook/disconnectedLine
        
        assertEquals(p.offHook(), "soundDialTone");
        assertEquals(p.invalidNumber(), "PlayMessage");
        try {
            p.offHook();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Warning, Transition: offHook");
        }

        assertEquals(p.onHook(), "disconnectedLine");
    }

    @Test
    public void TestCase18() {
        // offHook/soundDialTone invalidNumber/playMessage offHook/nul validNumber/slowBusyTone
        
        assertEquals(p.offHook(), "soundDialTone");
        assertEquals(p.invalidNumber(), "PlayMessage");
        try {
            p.offHook();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Warning, Transition: offHook");
        }

        assertEquals(p.validNumber(), "slowBusyTone");
    }

    @Test
    public void TestCase19() {
        // offHook/soundDialTone invalidNumber/playMessage validNumber/slowBusyTone onHook/disconnectedLine
        
        assertEquals(p.offHook(), "soundDialTone");
        assertEquals(p.invalidNumber(), "PlayMessage");
        assertEquals(p.validNumber(), "slowBusyTone");
        assertEquals(p.onHook(), "disconnectedLine");
    }

    @Test
    public void TestCase20() {
        // offHook/soundDialTone invalidNumber/playMessage validNumber/slowBusyTone validNumber/slowBusyTone
        
        assertEquals(p.offHook(), "soundDialTone");
        assertEquals(p.invalidNumber(), "PlayMessage");
        assertEquals(p.validNumber(), "slowBusyTone");
        assertEquals(p.validNumber(), "slowBusyTone");
    }

    @Test
    public void TestCase21() {
        // offHook/soundDialTone invalidNumber/playMessage invalidNumber/slowBusyTone onHook/disconnectedLine
        
        assertEquals(p.offHook(), "soundDialTone");
        assertEquals(p.invalidNumber(), "PlayMessage");
        assertEquals(p.invalidNumber(), "slowBusyTone");
        assertEquals(p.onHook(), "disconnectedLine");
    }

    @Test
    public void TestCase22() {
        // offHook/soundDialTone invalidNumber/playMessage invalidNumber/slowBusyTone validNumber/slowBusyTone
        
        assertEquals(p.offHook(), "soundDialTone");
        assertEquals(p.invalidNumber(), "PlayMessage");
        assertEquals(p.invalidNumber(), "slowBusyTone");
        assertEquals(p.validNumber(), "slowBusyTone");
    }

    @Test
    public void TestCase23() {
        // offHook/soundDialTone invalidNumber/playMessage I/I onHook/disconnectedLine
        
        assertEquals(p.offHook(), "soundDialTone");
        assertEquals(p.invalidNumber(), "PlayMessage");
        try {Thread.sleep(101); } catch (Exception ex) {}
        assertEquals(p.onHook(), "disconnectedLine");
    }

    @Test
    public void TestCase24() {
        // offHook/soundDialTone invalidNumber/playMessage I/I validNumber/slowBusyTone
        
        assertEquals(p.offHook(), "soundDialTone");
        assertEquals(p.invalidNumber(), "PlayMessage");
        try {Thread.sleep(101); } catch (Exception ex) {}
        assertEquals(p.validNumber(), "slowBusyTone");
    }

    @Test
    public void TestCase25() {
        // offHook/soundDialTone I/I onHook/disconnectedLine onHook/nul
        
        assertEquals(p.offHook(), "soundDialTone");
        try {Thread.sleep(101); } catch (Exception ex) {}
        assertEquals(p.onHook(), "disconnectedLine");
        try {
            p.onHook();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Idle, Transition: onHook");
        }
    }

    @Test
    public void TestCase26() {
        // offHook/soundDialTone I/I offHook/nul onHook/disconnectedLine
        
        assertEquals(p.offHook(), "soundDialTone");
        try {Thread.sleep(101); } catch (Exception ex) {}
        try {
            p.offHook();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Ready, Transition: offHook");
        }

        assertEquals(p.onHook(), "disconnectedLine");
    }

    @Test
    public void TestCase27() {
        // offHook/soundDialTone I/I offHook/nul validNumber/findConnection
        
        assertEquals(p.offHook(), "soundDialTone");
        try {Thread.sleep(101); } catch (Exception ex) {}
        try {
            p.offHook();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Ready, Transition: offHook");
        }

        assertEquals(p.validNumber(), "findConnection");
    }

    @Test
    public void TestCase28() {
        // offHook/soundDialTone I/I offHook/nul I/I validNumber/findConnection
        
        assertEquals(p.offHook(), "soundDialTone");
        try {Thread.sleep(101); } catch (Exception ex) {}
        try {
            p.offHook();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Ready, Transition: offHook");
        }
        try {Thread.sleep(101); } catch (Exception ex) {}

        assertEquals(p.validNumber(), "findConnection");
    }

    @Test
    public void TestCase29() {
        // offHook/soundDialTone I/I offHook/nul I/I I/I validNumber/slowBusyTone
        
        assertEquals(p.offHook(), "soundDialTone");
        try {Thread.sleep(101); } catch (Exception ex) {}
        try {
            p.offHook();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Ready, Transition: offHook");
        }
        try {Thread.sleep(101); } catch (Exception ex) {}
        try {Thread.sleep(101); } catch (Exception ex) {}

        assertEquals(p.validNumber(), "slowBusyTone");
    }

    @Test
    public void TestCase30() {
        // offHook/soundDialTone I/I validNumber/findConnection onHook/disconnectedLine
        
        assertEquals(p.offHook(), "soundDialTone");
        try {Thread.sleep(101); } catch (Exception ex) {}

        assertEquals(p.validNumber(), "findConnection");
        assertEquals(p.onHook(), "disconnectedLine");
    }

    @Test
    public void TestCase31() {
        // offHook/soundDialTone I/I validNumber/findConnection validNumber/continues
        
        assertEquals(p.offHook(), "soundDialTone");
        try {Thread.sleep(101); } catch (Exception ex) {}

        assertEquals(p.validNumber(), "findConnection");
        assertEquals(p.validNumber(), "continues");
    }

    @Test
    public void TestCase32() {
        // offHook/soundDialTone I/I invalidNumber/playMessage onHook/disconnectedLine
        
        assertEquals(p.offHook(), "soundDialTone");
        try {Thread.sleep(101); } catch (Exception ex) {}

        assertEquals(p.invalidNumber(), "PlayMessage");
        assertEquals(p.onHook(), "disconnectedLine");
    }

    @Test
    public void TestCase33() {
        // offHook/soundDialTone I/I invalidNumber/playMessage validNumber/slowBusyTone
        
        assertEquals(p.offHook(), "soundDialTone");
        try {Thread.sleep(101); } catch (Exception ex) {}

        assertEquals(p.invalidNumber(), "PlayMessage");
        assertEquals(p.validNumber(), "slowBusyTone");
    }

    @Test
    public void TestCase34() {
        // offHook/soundDialTone I/I I/I onHook/disconnectedLine onHook/nul
        
        assertEquals(p.offHook(), "soundDialTone");
        try {Thread.sleep(101); } catch (Exception ex) {}
        try {Thread.sleep(101); } catch (Exception ex) {}

        assertEquals(p.onHook(), "disconnectedLine");
        try {
            p.onHook();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Idle, Transition: onHook");
        }
    }

    @Test
    public void TestCase35() {
        // offHook/soundDialTone I/I I/I offHook/nul onHook/disconnectedLine
        
        assertEquals(p.offHook(), "soundDialTone");
        try {Thread.sleep(101); } catch (Exception ex) {}
        try {Thread.sleep(101); } catch (Exception ex) {}
        try {
            p.offHook();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Ready, Transition: offHook");
        }
        assertEquals(p.onHook(), "disconnectedLine");
    }

    @Test
    public void TestCase36() {
        // offHook/soundDialTone I/I I/I offHook/nul validNumber/findConnection
        
        assertEquals(p.offHook(), "soundDialTone");
        try {Thread.sleep(101); } catch (Exception ex) {}
        try {Thread.sleep(101); } catch (Exception ex) {}
        try {
            p.offHook();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Ready, Transition: offHook");
        }
        assertEquals(p.validNumber(), "findConnection");
    }

    @Test
    public void TestCase37() {
        // offHook/soundDialTone I/I I/I offHook/nul I/I validNumber/slowBusyTone
        
        assertEquals(p.offHook(), "soundDialTone");
        try {Thread.sleep(101); } catch (Exception ex) {}
        try {Thread.sleep(101); } catch (Exception ex) {}
        try {
            p.offHook();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Ready, Transition: offHook");
        }
        try {Thread.sleep(101); } catch (Exception ex) {}
        assertEquals(p.validNumber(), "slowBusyTone");
    }

    @Test
    public void TestCase38() {
        // offHook/soundDialTone I/I I/I validNumber/findConnection onHook/disconnectedLine
        
        assertEquals(p.offHook(), "soundDialTone");
        try {Thread.sleep(101); } catch (Exception ex) {}
        try {Thread.sleep(101); } catch (Exception ex) {}

        assertEquals(p.validNumber(), "findConnection");
        assertEquals(p.onHook(), "disconnectedLine");
    }

    @Test
    public void TestCase39() {
        // offHook/soundDialTone I/I I/I validNumber/findConnection validNumber/continues
        
        assertEquals(p.offHook(), "soundDialTone");
        try {Thread.sleep(101); } catch (Exception ex) {}
        try {Thread.sleep(101); } catch (Exception ex) {}

        assertEquals(p.validNumber(), "findConnection");
        assertEquals(p.validNumber(), "continues");
    }

    @Test
    public void TestCase40() {
        // offHook/soundDialTone I/I I/I invalidNumber/playMessage onHook/disconnectedLine
        
        assertEquals(p.offHook(), "soundDialTone");
        try {Thread.sleep(101); } catch (Exception ex) {}
        try {Thread.sleep(101); } catch (Exception ex) {}

        assertEquals(p.invalidNumber(), "PlayMessage");
        assertEquals(p.onHook(), "disconnectedLine");
    }

    @Test
    public void TestCase41() {
        // offHook/soundDialTone I/I I/I invalidNumber/playMessage validNumber/slowBusyTone
        
        assertEquals(p.offHook(), "soundDialTone");
        try {Thread.sleep(101); } catch (Exception ex) {}
        try {Thread.sleep(101); } catch (Exception ex) {}

        assertEquals(p.invalidNumber(), "PlayMessage");
        assertEquals(p.validNumber(), "slowBusyTone");
    }

    @Test
    public void TestCase42() {
        // offHook/soundDialTone I/I I/I I/I onHook/disconnectedLine
        
        assertEquals(p.offHook(), "soundDialTone");
        try {Thread.sleep(101); } catch (Exception ex) {}
        try {Thread.sleep(101); } catch (Exception ex) {}
        try {Thread.sleep(101); } catch (Exception ex) {}

        assertEquals(p.onHook(), "disconnectedLine");
    }

    @Test
    public void TestCase43() {
        // offHook/soundDialTone I/I I/I I/I validNumber/slowBusyTone
        
        assertEquals(p.offHook(), "soundDialTone");
        try {Thread.sleep(101); } catch (Exception ex) {}
        try {Thread.sleep(101); } catch (Exception ex) {}
        try {Thread.sleep(101); } catch (Exception ex) {}

        assertEquals(p.validNumber(), "slowBusyTone");
    }

    @Test
    public void TestCase44() {
        // validNumber/nul onHook/nul
        
        try {
            p.validNumber();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Idle, Transition: validNumber");
        }
        try {
            p.onHook();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Idle, Transition: onHook");
        }
    }

    @Test
    public void TestCase45() {
        // invalidNumber/nul onHook/nul
        
        try {
            p.invalidNumber();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Idle, Transition: invalidNumber");
        }
        try {
            p.onHook();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Idle, Transition: onHook");
        }
    }

    @Test
    public void TestCase46() {
        // I/I onHook/nul
        
        try {Thread.sleep(101); } catch (Exception ex) {}
        try {
            p.onHook();
        } catch (Exception e) {
            assertEquals(e.getMessage(), "State: PhoneLineFSM.Idle, Transition: onHook");
        }
    }
}
