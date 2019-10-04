package xyz.tofuboy.charms.charms;

import org.bukkit.entity.Player;

import java.util.ArrayList;

public class CharmPlayer {
        private Player player;
        private ArrayList<Charm> playersCharms = new ArrayList();
        private int totalCharms = 0;

        public CharmPlayer(Player player) {
            this.player = player;
        }

        public ArrayList<Charm> getPlayersCharms() {
            return this.playersCharms;
        }

        public boolean hasCharmsLoaded() {
            return this.playersCharms.size() > 0;
        }

        public Player getPlayer() {
            return this.player;
        }

        public int getTotalCharms() {
            return this.totalCharms;
        }

        public void setTotalCharms(int var1) {
            this.totalCharms = var1;
        }

}
