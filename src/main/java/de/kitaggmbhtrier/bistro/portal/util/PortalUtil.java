package de.kitaggmbhtrier.bistro.portal.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import de.kitaggmbhtrier.bistro.data.KindergartenChild;


@Component
public class PortalUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(PortalUtil.class);
	
	private static final String GIT_MVN_PLUGIN_FILENAME = "git.properties";
	private static final String GIT_MVN_PLUGIN_KEY_BUILDVERSION = "git.build.version";
	private static final String GIT_MVN_PLUGIN_KEY_COMMITDESCRIBE = "git.commit.id.describe";
	
	private String gitInfoCached = "";

	@PostConstruct
	private void computGitInfo() {
		final Properties gitProps = new Properties();
		try {
			InputStream resourceAsStream = PortalUtil.class.getClassLoader().getResourceAsStream(GIT_MVN_PLUGIN_FILENAME);
			if(resourceAsStream != null) {
				gitProps.load(resourceAsStream);
			}
		} catch(IOException e) {
			LOGGER.error("Could not load git information from " + GIT_MVN_PLUGIN_FILENAME, e);
		}
		StringBuilder sb = new StringBuilder();
		sb.append(gitProps.getProperty(GIT_MVN_PLUGIN_KEY_BUILDVERSION));
		sb.append(" [");
		sb.append(gitProps.getProperty(GIT_MVN_PLUGIN_KEY_COMMITDESCRIBE));
		sb.append("]");
		gitInfoCached = sb.toString();
	}

	public String getGitInfo() {
		return gitInfoCached;
	}
	
	public static int compare(KindergartenChild child1, KindergartenChild child2) {
		if(child1.getLastName().equals(child2.getLastName())) {
			return child1.getFirstName().compareTo(child2.getFirstName());
		} else {
			return child1.getLastName().compareTo(child2.getLastName());
		}
	}
}
