
package wotr;
/**
 * A magician can restore powers of an other NPC only one time. He can also give hps or corruption points (negative or positives ones) 
 * to frodo. * For example Gandalf is a good magician, he decreases the corruption of frodo while saruman (the evil one) 
 * increase these corruption points but they can both restor the powers of an other npc.
 *
 * @author GR1
 * @version 21/11/016
 */
public class Magician extends NPC
{
    /**
     * Constructor for Magicians.
     *@param name : of the NPC
     *@param description : a short description of th player ex : "dwarfs are sprinters, they are fast on short distances but long distances tires them."
     *@param corruptionPower : /corruption power owned by the NPC, they are used to give corruption points to frodo
     *@param hpPower : health points wich will be given or retrieved to Frodo
     *@param currentPlayer : //frodo
     */
    public Magician(String name, String description, int corruptionPower, int hpPower)
    {
        super(name, description, corruptionPower, hpPower);
    }

    /**
    *Use method, restore hp of frodo (max = 100) and restore power of an other npc
    *@return true if the power of the npc just have been used
    *@return false if the powers cannot be used
    */
    public boolean use(NPC npc){
        if (!this.getAlreadyUsed()){
        super.use(); //verifier si le return contenu dans ce use n'est pas terminal...
        npc.restorePower();
        this.setAlreadyUsed(true);
        return true;
    }
    else{
        return false;
    }
}
}
