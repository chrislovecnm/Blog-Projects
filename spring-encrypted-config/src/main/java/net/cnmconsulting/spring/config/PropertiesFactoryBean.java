package net.cnmconsulting.spring.config;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.jasypt.encryption.StringEncryptor;
import org.jasypt.properties.PropertyValueEncryptionUtils;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.support.PropertiesLoaderSupport;
import org.springframework.util.Assert;

/**
 * This code is based off of
 * {@link org.springframework.beans.factory.config.PropertiesFactoryBean}. It
 * also decrypts encoded passwords. Allows for making a properties file from a
 * classpath location available as Properties instance in a bean factory. Can be
 * used to populate any bean property of type Properties via a bean reference.
 * 
 * <p>
 * Supports loading from a properties file and/or setting local properties on
 * this FactoryBean. The created Properties instance will be merged from loaded
 * and local values. If neither a location nor local properties are set, an
 * exception will be thrown on initialization.
 * 
 * <p>
 * Can create a singleton or a new object on each request. Default is a
 * singleton.
 * 
 * @author Chris Love
 * @see #setLocation
 * @see #setProperties
 * @see #setLocalOverride
 * @see java.util.Properties
 */
public class PropertiesFactoryBean extends PropertiesLoaderSupport implements
		FactoryBean<Properties>, InitializingBean {

	private boolean singleton = true;

	private Properties singletonInstance;

	private final StringEncryptor stringEncryptor;
	

	public PropertiesFactoryBean(final StringEncryptor stringEncryptor) {
		super();
		Assert.notNull(stringEncryptor,"encryptor cannot be null");
		this.stringEncryptor = stringEncryptor;
	}

	/**
	 * Set whether a shared 'singleton' Properties instance should be created,
	 * or rather a new Properties instance on each request.
	 * <p>
	 * Default is "true" (a shared singleton).
	 */
	public final void setSingleton(boolean singleton) {
		this.singleton = singleton;
	}

	public final boolean isSingleton() {
		return this.singleton;
	}

	public final void afterPropertiesSet() throws IOException {
		if (this.singleton) {
			this.singletonInstance = createProperties();
		}
	}

	public final Properties getObject() throws IOException {
		if (this.singleton) {
			return this.singletonInstance;
		} else {
			return createProperties();
		}
	}

	public Class<Properties> getObjectType() {
		return Properties.class;
	}

	/**
	 * Template method that subclasses may override to construct the object
	 * returned by this factory. The default implementation returns the plain
	 * merged Properties instance.
	 * <p>
	 * Invoked on initialization of this FactoryBean in case of a shared
	 * singleton; else, on each {@link #getObject()} call.
	 * 
	 * @return the object returned by this factory
	 * @throws IOException
	 *             if an exception occured during properties loading
	 * @see #mergeProperties()
	 */
	protected Properties createProperties() throws IOException {
		return (Properties) createInstance();
	}

	/**
	 * This method scans for property values that are marked as encrypted
	 * and decryptes those values.
	 * 
	 * Template method that subclasses may override to construct the object
	 * returned by this factory. The default implementation returns the plain
	 * merged Properties instance.
	 * <p>
	 * Invoked on initialization of this FactoryBean in case of a shared
	 * singleton; else, on each {@link #getObject()} call.
	 * 
	 * @return the object returned by this factory
	 * @throws IOException
	 *             if an exception occured during properties loading
	 * @deprecated as of Spring 3.0, in favor of {@link #createProperties()}
	 */
	@Deprecated
	protected Object createInstance() throws IOException {
		Properties props = mergeProperties();
		for (Map.Entry<Object, Object> entry : props.entrySet()) {

			if ((entry.getValue() instanceof String)) {

				String value = (String) entry.getValue();
				if (PropertyValueEncryptionUtils.isEncryptedValue(value)) {
					String decrypted = PropertyValueEncryptionUtils.decrypt(
							value, this.stringEncryptor);
					props.put(entry.getKey(), decrypted);
				}
			}
		}
		
		return props;

	}

}
