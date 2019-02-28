package com.superempires.game.map.generation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.superempires.game.map.biome.Biome;
import com.superempires.game.map.tiling.Tile;
import com.superempires.game.util.Vector2i;

public class MapGenerator
{
	private List<Biome> biomes = new ArrayList<>();
	
	/**
	 * Registers a tile template
	 * @param template The template
	 * @param odds The odds of the tile generating (not a percentage) - 1 is super small, 100 is highest
	 */
	public void registerBiome(Biome b)
	{
		biomes.add(b);
	}
	
	public Tile[][] generateMap(final int WIDTH, final int HEIGHT)
	{
		Random rdm = new Random();
		
		Tile[][] tiles = new Tile[HEIGHT][WIDTH];
		
		Map<Vector2i, Biome> biomeCoords = new HashMap<>();
		
		Biome curBiome = null;
		
		int biomeX = 0;
		int biomeY = 0;
		
		int biomeMaxX = rdm.nextInt(100) + 50;
		int biomeMaxY = rdm.nextInt(100) + 50;
		
		for(int y = 0; y < HEIGHT; y++)
		{
			int biomeRandomX = 0;
			
			for(int x = 0; x < WIDTH; x++)
			{
				double temperature = calculateTemperature(y, HEIGHT, rdm);
				
				Vector2i coord = new Vector2i(x, y);
				
				boolean combined = biomeCoords.containsKey(coord);
				
				if(biomeX > biomeMaxX + biomeRandomX || (curBiome == null || combined))
				{
					if(!combined)
						curBiome = findBestBiome(temperature, rdm);
					else
						curBiome = biomeCoords.get(coord);
					
					biomeCoords.put(coord, curBiome);
					
					biomeX = 0;
				}
				
				biomeX++;
				
				curBiome.generateTile(tiles, x, y, temperature);
			}
			
			
			System.out.println(biomeX);
			
			biomeX = 0;
		}
		
		return tiles;
	}
	
	private static double calculateTemperature(int y, int worldHeight, Random rdm)
	{
		double closer = Math.min(y + 1, worldHeight - y);
		
		double min = (closer * 47.0 / worldHeight - 4.5) * 5.3 + 0.5;
		
		return rdm.nextDouble() * 4 + min;
	}
	
	protected List<Biome> findOkBiomes(double temperature)
	{
		List<Biome> goodBiomes = new ArrayList<>();
		
		for(Biome b : biomes)
		{
			if(b.isAcceptableTemperature(temperature))
				goodBiomes.add(b);
		}
		
		return goodBiomes;
	}
	
	protected Biome findBestBiome(double temperature, Random rdm)
	{
		float totalRarity = 0;
		List<Biome> goodBiomes = new ArrayList<>();
		
		for(Biome b : biomes)
		{
			boolean ok = b.isAcceptableTemperature(temperature);
			
			if(ok)
			{
				goodBiomes.add(b);
				totalRarity += b.getRarity();
			}
		}
		
		int rand = (int)(rdm.nextFloat() * totalRarity + 1);
		
		float previousRarity = 0;
		
		for(Biome b : goodBiomes)
		{
			previousRarity += b.getRarity();
			if(previousRarity >= rand)
				return b;
		}		
		
		System.out.println(goodBiomes);
		
		System.out.println(previousRarity + " >= " + rand);
		
		throw new IllegalStateException("NO VALID BIOME: " + temperature + " degrees!");
	}
}
