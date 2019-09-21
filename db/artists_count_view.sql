CREATE OR REPLACE VIEW artists_count_view AS
	SELECT 
		artists.id AS id,
		COUNT(DISTINCT bands.id) AS band_count
			FROM
				artists
			LEFT JOIN
				bands_artists
			ON
				artists.id = bands_artists.artist_id
			LEFT JOIN
				bands
			ON
				bands.id = bands_artists.band_id
			GROUP BY
				artists.id;