package hello.storage;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.*;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileSystemStorageService implements StorageService {

	private  Path rootLocation;
	private final String uuid = UUID.randomUUID().toString(); //.replace("-", "");
	private final StorageProperties properties;

	@Autowired
	public FileSystemStorageService(StorageProperties properties) {

		this.properties = properties;
		this.rootLocation = Paths.get(properties.getLocation());


	}

	@Override
	public void store(MultipartFile file) {
		String filename = StringUtils.cleanPath(file.getOriginalFilename());



		try {
			if (file.isEmpty()) {

				throw new StorageException("Failed to store empty file " + filename);
			}
			if (filename.contains("..")) {
				// This is a security check
				throw new StorageException(
						"Cannot store file with relative path outside current directory "
								+ filename);
			}
			try (InputStream inputStream = file.getInputStream()) {


				try{

					Path newProjectFolder = Paths.get(properties.getLocation() + "/" + uuid.substring(0,4));
					Files.createDirectory(newProjectFolder); //skapar ny projektmapp
					Files.copy(inputStream, newProjectFolder.resolve(filename),
							StandardCopyOption.REPLACE_EXISTING);
				}
				catch (FileAlreadyExistsException exd){
					throw new StorageException("FOLDER ALREADY EXISTS, " + filename, exd);
				}


			}
		}
		catch (IOException e) {
			throw new StorageException("Failed to store file " + filename, e);
		}
	}

	@Override
	public Stream<Path> loadAll() {

		try (Stream<Path> walk = Files.walk(Paths.get("simulations"),1)) {

			List<String> result = walk.filter(Files::isRegularFile)
					.map(x -> x.toString()).collect(Collectors.toList());

			result.forEach(System.out::println);

		} catch (IOException e) {
			e.printStackTrace();
		}


		try {
			return Files.walk(this.rootLocation, 1)
				.filter(path -> !path.equals(this.rootLocation))
				.map(this.rootLocation::relativize);
		}
		catch (IOException e) {
			throw new StorageException("Failed to read stored files", e);
		}

	}

	@Override
	public Path load(String folder,String filename) { //hittar filer i folders
		Path folderPath = Paths.get(folder);
		String p = folderPath.resolve(filename).toString();
		System.out.println("Loaded: " + p);
		return rootLocation.resolve(p); //lagar ihop fodlers med respektive filer

	}

	@Override
	public Resource loadAsResource(String folder,String filename) {
		try {
			Path file = load(folder,filename);
			Resource resource = new UrlResource(file.toUri());
			if (resource.exists() || resource.isReadable()) {
				return resource;
			}
			else {
				throw new StorageFileNotFoundException(
						"Could not read file: " + filename);

			}
		}
		catch (MalformedURLException e) {
			throw new StorageFileNotFoundException("Could not read file: " + filename, e);
		}
	}

	@Override
	public void deleteAll() {
		FileSystemUtils.deleteRecursively(rootLocation.toFile());
	}

	@Override
	public void init() {
		try {
			Files.createDirectories(rootLocation);
		}
		catch (IOException e) {
			throw new StorageException("Could not initialize storage", e);
		}
	}
}
