package com.superempires.game.map.biome;

import java.util.Random;

import com.superempires.game.map.tiling.PlainsTile;
import com.superempires.game.map.tiling.SnowTile;
import com.superempires.game.map.tiling.Tile;

public class ForestBiome extends Biome
{
	@Override
	public void generateTile(Tile[][] tiles, int x, int y, double temperature, Random rdm)
	{
		if(Biome.isFreezing(temperature))
			tiles[y][x] = new SnowTile(x, y, temperature, this);
		else
			tiles[y][x] = new PlainsTile(x, y, temperature, this);
	}

	@Override
	public boolean isAcceptableTemperature(double temperature)
	{
		return false;
	}
}