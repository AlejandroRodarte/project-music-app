DROP DATABASE IF EXISTS music_db;
CREATE DATABASE IF NOT EXISTS music_db;

USE music_db;

CREATE TABLE IF NOT EXISTS bands (
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image_path VARCHAR(255) NOT NULL,
    origin_country VARCHAR(255) NOT NULL,
    origin_year INT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW() ON UPDATE NOW()
);

CREATE TABLE IF NOT EXISTS albums (
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    image_path VARCHAR(255) NOT NULL,
    release_year INT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP DEFAULT NOW() ON UPDATE NOW(),
    band_id INT,
    CONSTRAINT fk_band 
		FOREIGN KEY (band_id) 
        REFERENCES bands(id) 
        ON DELETE CASCADE
);

CREATE TABLE songs (
	id INTEGER AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    track_number INT NOT NULL,
    duration INT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP ON UPDATE NOW(),
    album_id INT,
    CONSTRAINT fk_album 
		FOREIGN KEY (album_id) 
        REFERENCES albums(id) 
        ON DELETE CASCADE
);

CREATE TABLE song_details (
	id INT AUTO_INCREMENT PRIMARY KEY,
    lyrics TEXT NOT NULL,
    youtube_url VARCHAR(255) NOT NULL,
    song_id INT NOT NULL,
    CONSTRAINT fk_song 
		FOREIGN KEY (song_id) 
        REFERENCES songs(id) 
        ON DELETE CASCADE
);

CREATE TABLE artists (
	id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    image_path VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP ON UPDATE NOW()
);

CREATE TABLE genres (
	id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP ON UPDATE NOW()
);

CREATE TABLE bands_artists (
	band_id INT NOT NULL,
    artist_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP ON UPDATE NOW(),
    FOREIGN KEY(band_id) REFERENCES bands(id),
    FOREIGN KEY (artist_id) REFERENCES artists(id),
    PRIMARY KEY(band_id, artist_id)
);

CREATE TABLE bands_genres (
	band_id INT NOT NULL,
    genre_id INT NOT NULL,
    created_at TIMESTAMP DEFAULT NOW(),
    updated_at TIMESTAMP ON UPDATE NOW(),
    FOREIGN KEY(band_id) REFERENCES bands(id),
    FOREIGN KEY (genre_id) REFERENCES genres(id),
    PRIMARY KEY(band_id, genre_id)
);