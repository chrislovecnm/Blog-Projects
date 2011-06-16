/*
 * =============================================================================
 * 
 *   Copyright (c) 2007-2011, The CNM Consulting (http://cnmconsulting.net) and
 *   chrislovecnm (http://chrislovecnm.com)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package net.cnmconsulting.spring.config.util;

import java.io.File;
import java.io.IOException;
import java.security.Provider;
import java.util.Properties;

import org.jasypt.encryption.pbe.config.EnvironmentPBEConfig;
import org.jasypt.encryption.pbe.config.PBEConfig;
import org.jasypt.encryption.pbe.config.SimplePBEConfig;
import org.jasypt.salt.SaltGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.util.Assert;

/**
 * Based on {@link EnvironmentPBEConfig}
 * <p>
 * Implementation for {@link PBEConfig} which can retrieve configuration
 * values from Environment variables or system properties.
 * </p>
 * <p>
 * The name of the Environment variable or system property (JVM property) to
 * query for each parameter can be set with its corresponding 
 * <tt>setXName</tt> or <tt>setXSysProperty</tt> method.
 * </p>
 * <p>
 * As this class extends {@link SimplePBEConfig}, parameter values
 * can be also set with the usual <tt>setX</tt> methods.
 * </p>
 * <p>
 * For any of the configuration parameters, if its value is not configured
 * in any way, a <tt>null</tt> value will be returned by the
 * corresponding <tt>getX</tt> method. 
 * </p>
 * 
 * @since 1.0
 * 
 * @author Chris Love
 * 
 */
public class FilePBEConfig extends SimplePBEConfig {
	
	final private Logger logger = LoggerFactory.getLogger(this.getClass());
    
    private String algorithmName = null;
    private String keyObtentionIterationsName = null;
    private Resource passwordName = null;
    private String saltGeneratorClassNameName = null;
    private String providerNameName = null;
    private String providerClassNameName = null;
    private String poolSizeName = null;

    private String algorithmSysPropertyName = null;
    private String keyObtentionIterationsSysPropertyName = null;
    private String saltGeneratorClassNameSysPropertyName = null;
    private String providerNameSysPropertyName = null;
    private String providerClassNameSysPropertyName = null;
    private String poolSizeSysPropertyName = null;
    private String token = null;
    private String folderName = null;

	private boolean searchSystemEnvironment;
    

	/**
     * <p>
     * Creates a new <tt>FilePBEConfig</tt> instance.
     * </p>
     */
    public FilePBEConfig() {
        super();
    }

    public FilePBEConfig(String folderName, String token, boolean searchSystemEnvironment) {
        super();
        this.token = token;
        this.folderName = folderName;
        this.searchSystemEnvironment = true;
        
        if(searchSystemEnvironment) {
        	String path = resolveSystemProperty(folderName);
        	Assert.notNull(path);
        	path += File.separatorChar + token;
        	setPasswordName(new FileSystemResource(path));
        }
        	
    }


    /**
     * Retrieve the name of the Environment variable which value has been
     * loaded as the algorithm.
     *   
     * @return the name of the variable
     */
    public String getAlgorithmName() {
        return this.algorithmName;
    }


    /**
     * Set the config object to use the specified Environment variable to
     * load the value for the algorithm.
     * 
     * @param algorithmName the name of the Environment variable
     */
    public void setAlgorithmName(final String algorithmName) {
        this.algorithmName = algorithmName;
        if (algorithmName == null) {
            super.setAlgorithm(null);
        } else {
            this.algorithmSysPropertyName = null;
            super.setAlgorithm(System.getProperty(algorithmName));
        }
    }


    /**
     * Retrieve the name of the JVM system property which value has been
     * loaded as the algorithm.
     *   
     * @return the name of the property
     */
    public String getAlgorithmSysPropertyName() {
        return this.algorithmSysPropertyName;
    }


    /**
     * Set the config object to use the specified JVM system property to
     * load the value for the algorithm.
     * 
     * @param algorithmSysPropertyName the name of the property
     */
    public void setAlgorithmSysPropertyName(final String algorithmSysPropertyName) {
        this.algorithmSysPropertyName = algorithmSysPropertyName;
        if (algorithmSysPropertyName == null) {
            super.setAlgorithm(null);
        } else {
            this.algorithmName = null;
            super.setAlgorithm(System.getProperty(algorithmSysPropertyName));
        }
    }


    /**
     * Retrieve the name of the Environment variable which value has been
     * loaded as the key obtention iteration count.
     *   
     * @return the name of the variable
     */
    public String getKeyObtentionIterationsName() {
        return this.keyObtentionIterationsName;
    }


    /**
     * Set the config object to use the specified Environment variable to
     * load the value for the key obtention iteration count.
     * 
     * @param keyObtentionIterationsName the name of the Environment variable
     */
    public void setKeyObtentionIterationsName(final String keyObtentionIterationsName) {
        this.keyObtentionIterationsName = keyObtentionIterationsName;
        if (keyObtentionIterationsName == null) {
            super.setKeyObtentionIterations((Integer)null);
        } else {
            this.keyObtentionIterationsSysPropertyName = null;
            super.setKeyObtentionIterations(
                    System.getProperty(keyObtentionIterationsName));
        }
    }


    /**
     * Retrieve the name of the JVM system property which value has been
     * loaded as the key obtention iteration count.
     *   
     * @return the name of the property
     */
    public String getKeyObtentionIterationsSysPropertyName() {
        return this.keyObtentionIterationsSysPropertyName;
    }


    /**
     * Set the config object to use the specified JVM system property to
     * load the value for the key obtention iteration count.
     * 
     * @param keyObtentionIterationsSysPropertyName the name of the property
     */
    public void setKeyObtentionIterationsSysPropertyName(final String keyObtentionIterationsSysPropertyName) {
        this.keyObtentionIterationsSysPropertyName = keyObtentionIterationsSysPropertyName;
        if (keyObtentionIterationsSysPropertyName == null) {
            super.setKeyObtentionIterations((Integer)null);
        } else {
            this.keyObtentionIterationsName = null;
            super.setKeyObtentionIterations(
                    System.getProperty(keyObtentionIterationsSysPropertyName));
        }
    }


    /**
     * Retrieve the name of the File which contains 
     * the password.
     *   
     * @return the name of the variable
     */
    public Resource getPasswordName() {
        return this.passwordName;
    }


    /**
     * 
     * load the value for the password.
     * 
     * @param passwordName the name of the file
     */
    public void setPasswordName(final Resource passwordName) {
        this.passwordName = passwordName;
        if (passwordName == null) {
        	throw new RuntimeException("unable to load password");
        } else {
            //this.passwordSysPropertyName = null;
            Properties props = null;
            try {
				props =  PropertiesLoaderUtils.loadProperties(passwordName);
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
			Assert.notNull(props, "properties file cannot be null");
			String password = props.getProperty("bar");
			Assert.hasText(password, "password must have text");
            super.setPassword(password);
        }
    }


    /**
     * Retrieve the name of the JVM system property which value has been
     * loaded as the password.
     *   
     * @return the name of the property
     */
    //public String getPasswordSysPropertyName() {
     //   return this.passwordSysPropertyName;
    //}


    /**
     * Set the config object to use the specified JVM system property to
     * load the value for the password.
     * 
     * @param passwordSysPropertyName the name of the property
     */
    //public void setPasswordSysPropertyName(final String passwordSysPropertyName) {
      //  this.passwordSysPropertyName = passwordSysPropertyName;
        //if (passwordSysPropertyName == null) {
          //  super.setPassword(null);
        //} else {
          //  this.passwordName = null;
           // super.setPassword(System.getProperty(passwordSysPropertyName));
       // }
    //}


    /**
     * Retrieve the name of the Environment variable which value has been
     * loaded as the salt generator class name.
     * 
     * @since 1.3
     *   
     * @return the name of the variable
     */
    public String getSaltGeneratorClassNameName() {
        return this.saltGeneratorClassNameName;
    }

   
    /**
     * <p>
     * Set the config object to use the specified Environment variable to
     * load the value for the salt generator class name.
     * </p>
     * <p>
     * The salt generator class name which is set here must have a no-argument
     * constructor, so that it can be instantiated and passed to the encryptor.
     * </p>
     * 
     * @since 1.3
     * 
     * @param saltGeneratorClassNameName the name of the Environment variable
     */
    public void setSaltGeneratorClassNameName(final String saltGeneratorClassNameName) {
        this.saltGeneratorClassNameName = saltGeneratorClassNameName;
        if (saltGeneratorClassNameName == null) {
            super.setSaltGenerator(null);
        } else {
            this.saltGeneratorClassNameSysPropertyName = null;
            final String saltGeneratorClassName = System.getProperty(saltGeneratorClassNameName);
            super.setSaltGeneratorClassName(saltGeneratorClassName);
        }
    }


    /**
     * Retrieve the name of the JVM system property which value has been
     * loaded as the salt generator class name.
     * 
     * @since 1.3
     *   
     * @return the name of the property
     */
    public String getSaltGeneratorClassNameSysPropertyName() {
        return this.saltGeneratorClassNameSysPropertyName;
    }


    /**
     * <p>
     * Set the config object to use the specified JVM system property to
     * load the value for the salt generator class name.
     * </p>
     * <p>
     * The salt generator class name which is set here must have a no-argument
     * constructor, so that it can be instantiated and passed to the encryptor.
     * </p>
     * 
     * @since 1.3
     * 
     * @param saltGeneratorClassNameSysPropertyName the name of the property
     */
    public void setSaltGeneratorClassNameSysPropertyName(final String saltGeneratorClassNameSysPropertyName) {
        this.saltGeneratorClassNameSysPropertyName = saltGeneratorClassNameSysPropertyName;
        if (saltGeneratorClassNameSysPropertyName == null) {
            super.setSaltGenerator(null);
        } else {
            this.saltGeneratorClassNameName = null;
            final String saltGeneratorClassName = System.getProperty(saltGeneratorClassNameSysPropertyName);
            super.setSaltGeneratorClassName(saltGeneratorClassName);
        }
    }


    /**
     * Retrieve the name of the Environment variable which value has been
     * loaded as the provider name.
     * 
     * @since 1.3
     *   
     * @return the name of the variable
     */
    public String getProviderNameName() {
        return this.providerNameName;
    }


    /**
     * <p>
     * Set the config object to use the specified Environment variable to
     * load the value for the provider name.
     * </p>
     * 
     * @since 1.3
     * 
     * @param providerNameName the name of the Environment variable
     */
    public void setProviderNameName(final String providerNameName) {
        this.providerNameName = providerNameName;
        if (providerNameName == null) {
            super.setProviderName(null);
        } else {
            this.providerNameSysPropertyName = null;
            super.setProviderName(System.getProperty(providerNameName));
        }
    }


    /**
     * Retrieve the name of the JVM system property which value has been
     * loaded as the provider name.
     * 
     * @since 1.3
     *   
     * @return the name of the property
     */
    public String getProviderNameSysPropertyName() {
        return this.providerNameSysPropertyName;
    }


    /**
     * Set the config object to use the specified JVM system property to
     * load the value for the provider name.
     * 
     * @since 1.3
     * 
     * @param providerNameSysPropertyName the name of the property
     */
    public void setProviderNameSysPropertyName(final String providerNameSysPropertyName) {
        this.providerNameSysPropertyName = providerNameSysPropertyName;
        if (providerNameSysPropertyName == null) {
            super.setProviderName(null);
        } else {
            this.providerNameName = null;
            super.setProviderName(
                    System.getProperty(providerNameSysPropertyName));
        }
    }


    /**
     * Retrieve the name of the Environment variable which value has been
     * loaded as the provider class name.
     * 
     * @since 1.3
     *   
     * @return the name of the variable
     */
    public String getProviderClassNameName() {
        return this.providerClassNameName;
    }


    /**
     * <p>
     * Set the config object to use the specified Environment variable to
     * load the value for the provider class name.
     * </p>
     * <p>
     * The provider class name which is set here must have a no-argument
     * constructor, so that it can be instantiated and passed to the encryptor.
     * </p>
     * 
     * @since 1.3
     * 
     * @param providerClassNameName the name of the Environment variable
     */
    public void setProviderClassNameName(final String providerClassNameName) {
        this.providerClassNameName = providerClassNameName;
        if (providerClassNameName == null) {
            super.setProvider(null);
        } else {
            this.providerClassNameSysPropertyName = null;
            final String providerClassName = System.getProperty(providerClassNameName);
            super.setProviderClassName(providerClassName);
        }
    }


    /**
     * Retrieve the name of the JVM system property which value has been
     * loaded as the provider class name.
     * 
     * @since 1.3
     *   
     * @return the name of the property
     */
    public String getProviderClassNameSysPropertyName() {
        return this.providerClassNameSysPropertyName;
    }


    /**
     * <p>
     * Set the config object to use the specified JVM system property to
     * load the value for the provider class name.
     * </p>
     * <p>
     * The provider class name which is set here must have a no-argument
     * constructor, so that it can be instantiated and passed to the encryptor.
     * </p>
     * 
     * @since 1.3
     * 
     * @param providerClassNameSysPropertyName the name of the property
     */
    public void setProviderClassNameSysPropertyName(final String providerClassNameSysPropertyName) {
        this.providerClassNameSysPropertyName = providerClassNameSysPropertyName;
        if (providerClassNameSysPropertyName == null) {
            super.setProvider(null);
        } else {
            this.providerClassNameName = null;
            final String providerClassName = System.getProperty(providerClassNameSysPropertyName);
            super.setProviderClassName(providerClassName);
        }
    }
    

    /**
     * Retrieve the name of the Environment variable which value has been
     * loaded as the value for the poolSize
     * property.
     * 
     * @since 1.7
     *   
     * @return the name of the variable
     */
    public String getPoolSizeName() {
        return this.poolSizeName;
    }



    /**
     * <p>
     * Set the config object to use the specified Environment variable to
     * load the value for the poolSize
     * property.
     * </p>
     * 
     * @since 1.7
     * 
     * @param poolSizeName the name of the Environment variable
     */
    public void setPoolSizeName(final String poolSizeName) {
        this.poolSizeName = poolSizeName;
        if (poolSizeName == null) {
            super.setPoolSize((String)null);
        } else {
            this.poolSizeSysPropertyName = null;
            super.setPoolSize(System.getProperty(poolSizeName));
        }
    }



    /**
     * Retrieve the name of the JVM system property which value has been
     * loaded as the value for the poolSize
     * property.
     * 
     * @since 1.7
     *   
     * @return the name of the property
     */
    public String getPoolSizeSysPropertyName() {
        return this.poolSizeSysPropertyName;
    }



    /**
     * <p>
     * Set the config object to use the specified JVM system property to
     * load the value for the useLenientSaltSizeCheck
     * property.
     * </p>
     * 
     * @since 1.7
     * 
     * @param poolSizeSysPropertyName the name of the property
     */
    public void setPoolSizeSysPropertyName(final String poolSizeSysPropertyName) {
        this.poolSizeSysPropertyName = poolSizeSysPropertyName;
        if (poolSizeSysPropertyName == null) {
            super.setPoolSize((String)null);
        } else {
            this.poolSizeName = null;
            super.setPoolSize(System.getProperty(poolSizeSysPropertyName));
        }
    }
    
    
    
    
    
    
    
    

    
    public void setAlgorithm(final String algorithm) {
        this.algorithmName = null;
        this.algorithmSysPropertyName = null;
        super.setAlgorithm(algorithm);
    }



    public void setKeyObtentionIterations(final Integer keyObtentionIterations) {
        this.keyObtentionIterationsName = null;
        this.keyObtentionIterationsSysPropertyName = null;
        super.setKeyObtentionIterations(keyObtentionIterations);
    }


    public void setKeyObtentionIterations(final String keyObtentionIterations) {
        this.keyObtentionIterationsName = null;
        this.keyObtentionIterationsSysPropertyName = null;
        super.setKeyObtentionIterations(keyObtentionIterations);
    }


    public void setSaltGenerator(final SaltGenerator saltGenerator) {
        this.saltGeneratorClassNameName = null;
        this.saltGeneratorClassNameSysPropertyName = null;
        super.setSaltGenerator(saltGenerator);
    }


    public void setSaltGeneratorClassName(final String saltGeneratorClassName) {
        this.saltGeneratorClassNameName = null;
        this.saltGeneratorClassNameSysPropertyName = null;
        super.setSaltGeneratorClassName(saltGeneratorClassName);
    }


    public void setProviderName(final String providerName) {
        this.providerNameName = null;
        this.providerNameSysPropertyName = null;
        super.setProviderName(providerName);
    }

    
    
    public void setProvider(final Provider provider) {
        this.providerClassNameName = null;
        this.providerClassNameSysPropertyName = null;
        super.setProvider(provider);
    }


    public void setProviderClassName(final String providerClassName) {
        this.providerClassNameName = null;
        this.providerClassNameSysPropertyName = null;
        super.setProviderClassName(providerClassName);
    }


    public void setPoolSize(final Integer poolSize) {
        this.poolSizeName = null;
        this.poolSizeSysPropertyName = null;
        super.setPoolSize(poolSize);
    }


    public void setPoolSize(final String poolSize) {
        this.poolSizeName = null;
        this.poolSizeSysPropertyName = null;
        super.setPoolSize(poolSize);
    }

    
    public String getToken() {
		return token;
	}



	public void setToken(String token) {
		this.token = token;
	}



	public String getFolderName() {
		return folderName;
	}



	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}
	
	protected String resolveSystemProperty(String key) {
		try {
			String value = System.getProperty(key);
			if (value == null && this.searchSystemEnvironment) {
				value = System.getenv(key);
			}
			return value;
		}
		catch (Throwable ex) {
			if (logger.isDebugEnabled()) {
				logger.debug("Could not access system property '" + key + "': " + ex);
			}
			return null;
		}
	}
}

