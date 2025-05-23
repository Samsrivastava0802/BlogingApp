package com.samridhi.blogingapp.data.network.models.response

import com.google.gson.annotations.SerializedName
data class PostInformation(
    val id: Int?,
    val date: String?,
    val date_gmt: String?,
    val guid: Guid?,
    val modified: String?,
    val modified_gmt: String?,
    val slug: String?,
    val status: String?,
    val type: String?,
    val link: String?,
    val title: Title?,
    val content: Content?,
    val excerpt: Content?,
    val author: Int?,
    val featured_media: Int?,
    val comment_status: String?,
    val ping_status: String?,
    val sticky: Boolean?,
    val template: String?,
    val format: String?,
    val meta: Meta,
    val categories: List<Int>,
    val tags: List<Int>,
    val class_list: List<String>,
    val aioseo_notices: Any?,
    val jetpack_publicize_connections: Any?,
    val jetpack_featured_media_url: String?,
    val jetpack_likes_enabled: Boolean?,
    val jetpack_shortlink: String?,
    val jetpack_related_posts: List<Any>,
    val jetpack_sharing_enabled: Boolean?,
    val _links: Links?,
)

data class Guid(
    val rendered: String?
)

data class Title(
    val rendered: String?
)

data class Content(
    val rendered: String?,
    val protected: Boolean
)

data class Meta(
    val jetpack_post_was_ever_published: Boolean,
    val _jetpack_newsletter_access: String,
    val _jetpack_dont_email_post_to_subs: Boolean,
    val _jetpack_newsletter_tier_id: Int,
    val _jetpack_memberships_contains_paywalled_content: Boolean,
    val _jetpack_memberships_contains_paid_content: Boolean,
    val footnotes: String,
    val jetpack_publicize_message: String,
    val jetpack_publicize_feature_enabled: Boolean,
    val jetpack_social_post_already_shared: Boolean,
    val jetpack_social_options: JetpackSocialOptions,
)

data class JetpackSocialOptions(
    val image_generator_settings: ImageGeneratorSettings,
    val version: Int,
)

data class ImageGeneratorSettings(
    val template: String,
    val enabled: Boolean,
)

data class Links(
    val self: List<Self> = emptyList(),
    val collection: List<Collection> = emptyList(),
    val about: List<Collection> = emptyList(),
    val author: List<Author> = emptyList(),
    val replies: List<Author> = emptyList(),
    @SerializedName("version-history")
    val versionHistory: List<VersionHistory> = emptyList(),
    @SerializedName("predecessor-version")
    val predecessorVersion: List<PredecessorVersion> = emptyList(),
    @SerializedName("wp:featuredmedia")
    val wpFeaturedMedia: List<Author> = emptyList(),
    @SerializedName("wp:attachment")
    val wpAttachment: List<Collection> = emptyList(),
    @SerializedName("wp:term")
    val wpTerm: List<WpTerm> = emptyList(),
    val curies: List<Curies> = emptyList(),
)

data class Self(
    val href: String,
    val targetHints: TargetHints,
)

data class TargetHints(
    val allow: List<String>,
)

data class Allow(
    val allow: List<String>,
)

data class Collection(
    val href: String,
)

data class Author(
    val href: String,
    val embeddable: Boolean,
)

data class VersionHistory(
    val href: String,
    val count: Int,
)

data class PredecessorVersion(
    val id: Int,
    val href: String,
)

data class WpTerm(
    val taxonomy: String,
    val embeddable: Boolean,
    val href: String,
)

data class Curies(
    val name: String,
    val href: String,
    val templated: Boolean,
)
