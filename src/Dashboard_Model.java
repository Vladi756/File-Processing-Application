package cw2;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.concurrent.atomic.AtomicLong;

import cw2.ChartFactory.ChartType;
import cw2.datadisplay.DataComponent;

// Contains all the data and the logic of the application. 

public class Dashboard_Model {
	private Dashboard_View view;

	public Dashboard_Model(Dashboard_View v) {
		this.view = v;
	}

	public void updateMenu(ChartType cT) {
		final DataComponent dc = this.view.getDataComponentObjects().get(cT);
		if (!this.view.getDataComponents().remove(dc)) {
			this.view.getDataComponentPanel().add(dc);
			this.view.getDataComponents().add(dc);
		} else {
			this.view.getDataComponentPanel().remove(dc);
		}
		this.view.updateData();
		this.view.revalidate();
		this.view.repaint();
	}

	public static long calculateSize(final Path path) {

		final AtomicLong size = new AtomicLong(0);

		try {
			Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) {
					size.addAndGet(attrs.size());
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult visitFileFailed(final Path file, final IOException exc) {
					System.out.println("skipped: " + file + " (" + exc + ")");
					// Skip folders that can't be traversed
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(final Path dir, final IOException exc) {
					if (exc != null)
						System.out.println("had trouble traversing: " + dir + " (" + exc + ")");
					// Ignore errors traversing a folder
					return FileVisitResult.CONTINUE;
				}
			});
		} catch (final IOException e) {
			throw new AssertionError("walkFileTree will not throw IOException if the FileVisitor does not");
		}

		return size.get();
	}

	public void setFolder(final File folder) {
		final File[] contents = folder.listFiles(new FileFilter() {
			@Override
			public boolean accept(final File pathname) {
				return pathname.isDirectory();
			}
		});
		this.view.getMap().clear();
		for (int i = 0; i < contents.length; i++) {
			final String label = contents[i].getName();
			final Number value = calculateSize(contents[i].toPath());
			this.view.getMap().put(label, value);
		}
		this.view.getControlPanel().setFolder(folder);
		this.view.updateData();
	}
}
