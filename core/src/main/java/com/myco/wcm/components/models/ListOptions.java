package com.myco.wcm.components.models;

import com.adobe.acs.commons.genericlists.GenericList;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.Via;
import org.apache.sling.models.annotations.injectorspecific.Self;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;

import javax.annotation.PostConstruct;
import java.util.List;


@Model(
	adaptables = SlingHttpServletRequest.class,
	defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ListOptions {

	@Self
	private SlingHttpServletRequest request;

	@Self
	@Via("resource")
	private Resource resource;

	@SlingObject
	private ResourceResolver resourceResolver;


	private List<GenericList.Item> listOptions;

	@PostConstruct
	// PostConstructs are called after all the injection has occurred, but before the Model object is returned for use.
	private void init() {
		// Note that @PostConstruct code will always be executed on Model instantiation.
		// If the work done in PostConstruct is expensive and not always used in the consumption of the model, it is
		// better to lazy-execute the logic in the getter and persist the result in  model state if it is requested again.
		Page listPage = resourceResolver.adaptTo(PageManager.class).getPage("/etc/acs-commons/lists/sharethis-services");
		GenericList list = listPage.adaptTo(GenericList.class);
		this.listOptions = list.getItems();

	}



	public List<GenericList.Item> getListOptions(){
		return this.listOptions;
	}



}
