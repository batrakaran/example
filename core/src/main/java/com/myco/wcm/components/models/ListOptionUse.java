package com.myco.wcm.components.models;

import com.adobe.acs.commons.genericlists.GenericList;
import com.adobe.cq.sightly.WCMUsePojo;
import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.resource.ResourceResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ListOptionUse extends WCMUsePojo {

	private static final Logger LOG = LoggerFactory.getLogger(ListOptionUse.class);

	private List<GenericList.Item> listOptions = null;

	@Override
	public void activate() throws Exception {

		ResourceResolver resourceResolver = getResourceResolver();
		PageManager pageManager = resourceResolver.adaptTo(PageManager.class);
		Page listPage = pageManager.getPage("/etc/acs-commons/lists/sharethis-services");
		GenericList list = listPage.adaptTo(GenericList.class);
		listOptions = list.getItems();

	}

	public List<GenericList.Item> getListOptions() {
		return this.listOptions;
	}

}
