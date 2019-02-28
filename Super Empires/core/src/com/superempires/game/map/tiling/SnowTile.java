package com.superempires.game.map.tiling;

import com.superempires.game.map.biome.Biome;
import com.superempires.game.registry.GameRegistry;

public class SnowTile extends Tile
{
	static
	{
		GameRegistry.registerTexture("tile-snow", "tiles/tile-snow.png");
	}
	
	public SnowTile(float x, float y, double temperature, Biome biome)
	{
		super(x, y, temperature, GameRegistry.getTexture("tile-snow"), biome);
	}
	
	@Override
	public boolean isWalkable()
	{
		return true;
	}

	@Override
	public double getTravelTime()
	{
		return 1.5;
	}
}