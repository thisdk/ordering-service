package io.thisdk.github.ordering.dao

import io.thisdk.github.ordering.bean.CmsUser

interface CmsUserDao {

    fun login(username: String, password: String): CmsUser?

    fun register(cmsUser: CmsUser): CmsUser?

}